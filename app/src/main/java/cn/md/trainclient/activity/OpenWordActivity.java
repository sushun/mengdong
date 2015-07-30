package cn.md.trainclient.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.md.trainclient.R;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

public class OpenWordActivity extends Activity {
	
	private String docPath = "/storage/emulated/0/";
//	private String docName = "P020150320344044740414.doc";
	private String docName = "fy.doc";
	private String savePath = "/storage/emulated/0/";	
	    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openword);
        String name = docName.substring(0, docName.indexOf("."));
        try {
        	if(!(new File(savePath+name).exists()))
        		new File(savePath+name).mkdirs();
            convert2Html(docPath+docName,savePath+name+".html");
        } catch (Exception e){
            e.printStackTrace();
        }
        //WebView������ʾ����html�ļ�
        WebView webView = (WebView)this.findViewById(R.id.office);
        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);    
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        Log.e("url", savePath+name);
        webView.loadUrl("file://"+savePath+name+".html");
    }
    
    /**
     * word�ĵ�ת��html��ʽ 
     * */
    public void convert2Html(String fileName, String outPutFile)  
            throws TransformerException, IOException,  
            ParserConfigurationException {  
    	HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
        		DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());       
        
        //����ͼƬ·��
        wordToHtmlConverter.setPicturesManager(new PicturesManager()  
         {  
             public String savePicture( byte[] content,  
                     PictureType pictureType, String suggestedName,  
                     float widthInches, float heightInches )  
             {  
            	 String name = docName.substring(0,docName.indexOf("."));
                 return name+"/"+suggestedName;  
             }  
         } );
    
        //����ͼƬ
       List<Picture> pics=wordDocument.getPicturesTable().getAllPictures();  
        if(pics!=null){  
            for(int i=0;i<pics.size();i++){  
                Picture pic = (Picture)pics.get(i);  
                System.out.println( pic.suggestFullFileName()); 
                try {  
                	String name = docName.substring(0,docName.indexOf("."));
                    pic.writeImageContent(new FileOutputStream(savePath+ name + "/"
                            + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {  
                    e.printStackTrace();  
                }    
            }  
        }
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();  
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);
  
        TransformerFactory tf = TransformerFactory.newInstance();  
        Transformer serializer = tf.newTransformer();  
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");  
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);  
        out.close();  
        //����html�ļ�
        writeFile(new String(out.toByteArray()), outPutFile); 
    }
    
    /**
     * ��html�ļ����浽sd��
     * */
    public void writeFile(String content, String path) {  
        FileOutputStream fos = null;  
        BufferedWriter bw = null;  
        try {  
            File file = new File(path);  
            if(!file.exists()){
            	file.createNewFile();
            }            	
            fos = new FileOutputStream(file);  
            bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));  
            bw.write(content);  
        } catch (FileNotFoundException fnfe) {  
            fnfe.printStackTrace();  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
        } finally {  
            try {  
                if (bw != null)  
                    bw.close();  
                if (fos != null)  
                    fos.close();  
            } catch (IOException ie) {  
            }  
        }  
    }
}
