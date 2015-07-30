package cn.md.trainclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cn.md.trainclient.R;

/**
 * User: su
 * Date: 2015-07-10.
 */
public class LogInActivity extends BaseActivity {
    private Button mConfirm;
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_log_in);
        mConfirm = (Button) findViewById(R.id.confirm);
        initView();
//       final LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setName("");
//        RequestManager.getInstance().startRequest(new OnRequestResponseListener() {
//            @Override
//            public void onRequestSuccess(long requestId, Request request, SuccessResult successResult) {
//                LoginResponse response= (LoginResponse)successResult.getResponse();
//
//            }
//        },loginRequest);

    }

    private void initView() {
        actionBar.setTitle("登录");
//        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(LogInActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
