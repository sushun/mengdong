package cn.md.trainclient.client;

import android.net.http.AndroidHttpClient;
import cn.md.trainclient.api.ApiErrorCode;
import cn.md.trainclient.api.ApiException;
import cn.md.trainclient.api.ApiField;
import cn.md.trainclient.api.ApiMeta;
import cn.md.trainclient.manager.ConfigManager;
import cn.md.trainclient.request.ApiRequest;
import cn.md.trainclient.response.ApiResponse;
import cn.md.trainclient.utils.Loger;
import cn.md.trainclient.utils.Logger;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ApiServiceClient {
    private static final String TAG = ApiServiceClient.class.getSimpleName();
    private static final boolean PRINT_PARAMS_DEBUG = true;

    private Gson gson;

    public ApiServiceClient(Gson gson) {
        this.gson = gson;
    }

    public synchronized <REQ extends ApiRequest, RES extends ApiResponse> RES execute(final REQ request) throws IOException, ApiException {
        //这里是根据访问的request来拿到枚举类型中的枚举对象
        final ApiMeta meta = ApiMeta.getApiMeta(request.getClass());
        // 这是根据拿到的数据进行url拼装只是拼接了前面的一小部分
        final String url = ConfigManager.getServerUrl() + meta.getUri();
        AndroidHttpClient httpClient = AndroidHttpClient.newInstance("eshifu_hclient");  // agent --> ???
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);    // 请求超时
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);            // 读取超时
        if (meta.getMethod().equals("post")) {
            return doPost(request, meta, url, httpClient);
        }
        return doGet(request, meta, url, httpClient);
    }

    private <REQ extends ApiRequest, RES extends ApiResponse> RES doPost(REQ request, ApiMeta meta, String url, AndroidHttpClient httpClient) {
        final List<NameValuePair> params = convertRequestToNameValuePair(request);
        // 打印参数
        if (PRINT_PARAMS_DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for (NameValuePair value : params) {
                sb.append(value.getName());
                sb.append(" : ");
                sb.append(value.getValue());
                sb.append(", ");
            }
            sb.append(" ]");
            Logger.i(TAG, sb.toString());
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                entity.consumeContent();
                Logger.e(TAG, ">>>> error_code(" + meta.getUri() + ") : " + httpResponse.getStatusLine().getStatusCode() + " >>>>");
                throw new IOException("status: " + httpResponse.getStatusLine());
            }
            String content = convertStreamToString(httpResponse.getEntity().getContent());
            Logger.e(TAG, "<<<< content : " + content);
            ApiResponse apiResponse = parse(content, meta.getResponseType());
            return (RES) apiResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            httpClient.close();
        }
    }

    private <REQ extends ApiRequest, RES extends ApiResponse> RES doGet(REQ request, ApiMeta meta, String url, AndroidHttpClient httpClient) {
        try {
            // 这个是完整的拼接url这里应该是运用了注解的知识把把字符串拼接完整了
            final String httpUrl = url + "?" + convertRequestToString(request);
            Loger.v("ApiServiceClient", httpUrl);
            HttpGet httpGet = new HttpGet(httpUrl);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                Logger.e(TAG, ">>>> error_code(" + meta.getUri() + ") : " + httpResponse.getStatusLine().getStatusCode() + " >>>>");
                throw new IOException("status: " + httpResponse.getStatusLine());
            }
            String content = convertStreamToString(httpResponse.getEntity().getContent());
            Logger.e(TAG, "<<<< content : " + content);
            ApiResponse apiResponse = parse(content, meta.getResponseType());
            return (RES) apiResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            httpClient.close();
        }
    }

    private <RES extends ApiResponse> RES parse(String content, Class<RES> classType) {
        try {
            return gson.fromJson(content, classType);  // change
        } catch (final RuntimeException e) {
            try {
                return gson.fromJson("{\"noTitleList\":"+content+"}", classType);
            } catch (final RuntimeException e1) {
                e1.printStackTrace();
                Logger.e(TAG, content + " ====> parse exception: " + e.getMessage());
                throw new ApiException(ApiErrorCode.GSON_ERROR, e.getMessage());
            }
        }
    }

    /**
     * 用于get请求
     */
    private String convertRequestToString(ApiRequest request) {
        final StringBuilder sbResult = new StringBuilder();
        Class<?> classType = request.getClass();
        while (true) {

            //这里用到java反射机制
            sbResult.append(convertFieldsToString(request, classType.getDeclaredFields()));
            if (classType.getGenericSuperclass() == null) {
                break;
            }
            classType = classType.getSuperclass();
        }
        return sbResult.toString();
    }

    /**
     * 用于get请求
     */
    private String convertFieldsToString(ApiRequest request, Field[] fields) {
        final StringBuilder sbResult = new StringBuilder();
        int i = 1;
        for (final Field field : fields) {
            if (!field.isAnnotationPresent(ApiField.class)) {
                continue;
            }
            Object value = null;
            try {
                field.setAccessible(true);
                value = field.get(request);
                if (value != null) {
                    final String paramName = field.getAnnotation(ApiField.class).paramName();
                    if (i != 1)
                        sbResult.append("&");
                    sbResult.append(paramName);
                    sbResult.append("=");
                    sbResult.append(String.valueOf(value));
                    i++;
                }
            } catch (final Exception e) {
                Logger.w("convert request string failed.", e.getMessage());
            }
        }
        return sbResult.toString();
    }

    /**
     * 用于post请求
     */
    private List<NameValuePair> convertRequestToNameValuePair(ApiRequest request) {
        List<NameValuePair> pairs = new ArrayList<>();

        Class<?> classType = request.getClass();

        Field[] fields = classType.getDeclaredFields();

        for (final Field field : fields) {
            if (!field.isAnnotationPresent(ApiField.class)) {
                continue;
            }
            Object value;
            try {
                field.setAccessible(true);
                value = field.get(request);
                if (value != null) {
                    final String paramName = field.getAnnotation(ApiField.class).paramName();
                    pairs.add(new BasicNameValuePair(paramName, String.valueOf(value)));
                }
            } catch (final Exception e) {
                Logger.w("convert request string failed.", e.getMessage());
            }
        }

        return pairs;
    }

    /**
     * 将返回结果转换成string
     */
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException ignored) {
        } finally {
            try {
                is.close();
            } catch (IOException ignored) {
            }
        }
        return sb.toString();
    }
}
