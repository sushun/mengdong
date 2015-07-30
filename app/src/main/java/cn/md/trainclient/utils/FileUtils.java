package cn.md.trainclient.utils;

import android.content.Context;
import android.graphics.*;
import android.os.Environment;
import android.os.StatFs;
import java.io.*;
import java.util.List;

/**
 * @author sush
 */
public class FileUtils {
    public static final String PIC_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "eshifu" + File.separator;

    public static boolean isSDCardNormal() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 记录日志
     */
    public static void recordLog2(Context context, String content) {
        if (content != null) {
            FileOutputStream fos = null;
            try {
                fos = context.openFileOutput("system_log.txt", Context.MODE_PRIVATE);
                fos.write(content.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void recordLog(String content) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String fileSavePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/eshifu/" + "log.txt";

            File file = new File(fileSavePath);
            FileWriter filerWriter = null;
            BufferedWriter bufWriter = null;
            try {
                filerWriter = new FileWriter(file, true); //后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
                bufWriter = new BufferedWriter(filerWriter);
                bufWriter.write(content);
                bufWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufWriter != null) {
                        bufWriter.close();
                    }
                    if (filerWriter != null) {
                        filerWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //压缩图片尺寸
    public static Bitmap compressBySize(String pathName, int targetWidth, int targetHeight) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        Bitmap bitmap;
        BitmapFactory.decodeFile(pathName, opts);

        float imgWidth = opts.outWidth;
        float imgHeight = opts.outHeight;
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        opts.inSampleSize = 1;
        if (widthRatio > 1 || heightRatio > 1) {
            if (widthRatio > heightRatio) {
                opts.inSampleSize = widthRatio;
            } else {
                opts.inSampleSize = heightRatio;
            }
        }
        //设置好缩放比例后，加载图片进内容；
        opts.inJustDecodeBounds = false;
        opts.inPurgeable = true;
        opts.inInputShareable = true; // 这个两个属性可以有效防止内存溢出: 一个是代表可以擦除的，一个代表可以共享
        bitmap = BitmapFactory.decodeFile(pathName, opts);
        return bitmap;
    }

    public static void saveBitmap2File(Bitmap bm, String filePath) {
        File dirFile = new File(filePath);
        if (dirFile.exists()) {
            dirFile.delete();  //删除原图片
        }
        BufferedOutputStream bos = null;
        try {
            File myCaptureFile = new File(filePath);
            bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);  //100表示不进行压缩，70表示压缩率为30%
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bm != null) {
            bm.recycle();
        }
    }

    public static Bitmap getBitmap4File(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fis = new FileInputStream(file);
        return BitmapFactory.decodeStream(fis);
    }

    /**
     * 添加文字水印到图片
     */
    public static Bitmap waterMark(Context context, Bitmap bitmap, List<String> texts) {
        if (bitmap == null) {
            return null;
        }
        Bitmap.Config bitmapConfig = bitmap.getConfig();
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);

        int scale1, scale2;
        int scaleTemp = 2;
        scale1 = bitmap.getWidth() / 600;
        scale2 = bitmap.getHeight() / 600;
        if (scale1 > 1 || scale2 > 1) {
            if (scale1 > scale2) {
                scaleTemp = scale2;
            } else {
                scaleTemp = scale1;
            }
        }

        float scale = context.getResources().getDisplayMetrics().density;
        int textSize = (int) (4.0 * scale * scaleTemp);

        int bitmapHeight = bitmap.getHeight();

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.rgb(61, 61, 61));
        paint.setTextSize(textSize);
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);

        int textLength = texts.size();
        for (int i = 0; i < texts.size(); i++) {
            Rect bounds = new Rect();
            paint.getTextBounds(texts.get(i), 0, texts.get(i).length(), bounds);
            int y = bitmapHeight - ((textLength - i) * bounds.height()) + ((i + 1) * scaleTemp * 5) - 20 * scaleTemp;

            canvas.drawText(texts.get(i), 20 * scaleTemp, y, paint);
        }
        return bitmap;
    }

    /**
     * SD卡是否正常且空间可用
     * @return
     */
    public static boolean isSDCardReady() {
        final String status = Environment.getExternalStorageState();
        long sdCardFreeSize;
        if (status.equals(Environment.MEDIA_MOUNTED)) {

            final File sdCardDir = Environment.getExternalStorageDirectory();
            final StatFs sf = new StatFs(sdCardDir.getPath());
            final long blockSize = sf.getBlockSize();
            final long availCount = sf.getAvailableBlocks();

            sdCardFreeSize = availCount * blockSize / 1024;
            if (sdCardFreeSize > 10 * 1024) {
                return true;
            }
        }
        return false;
    }
}