package cn.md.trainclient.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import cn.md.trainclient.R;
import com.itsrts.pptviewer.PPTViewer;

public class OpenPPTActivity extends Activity {

    PPTViewer pptViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openppt);
        pptViewer = (PPTViewer) findViewById(R.id.pptviewer);
        String path = Environment.getExternalStorageDirectory()
                + "/123.ppt";
        pptViewer.setNext_img(R.drawable.next).setPrev_img(R.drawable.prev)
                .setSettings_img(R.drawable.settings)
                .setZoomin_img(R.drawable.zoomin)
                .setZoomout_img(R.drawable.zoomout);
        pptViewer.loadPPT(this, path);

    }
}
