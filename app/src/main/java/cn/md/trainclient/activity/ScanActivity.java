package cn.md.trainclient.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.md.trainclient.R;
import cn.md.trainclient.qrCode.MipcaActivityCapture;

/**
 * Created by jindongping on 15/7/17.
 */
public class ScanActivity extends BaseActivity {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_scan_qt);
        initView();
    }

    private void initView() {
        actionBar.setTitle("个人中心");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        mTextView = (TextView) findViewById(R.id.result);
        mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ScanActivity.this, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    mTextView.setText(bundle.getString("result"));
                    mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                }
                break;
        }
    }

}
