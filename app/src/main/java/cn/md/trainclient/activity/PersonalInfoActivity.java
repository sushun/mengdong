package cn.md.trainclient.activity;

import android.os.Bundle;

import cn.md.trainclient.R;

/**
 * User: sush
 * Date: 2015-07-28.
 */
public class PersonalInfoActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal_info);
        
        initView();
    }

    private void initView() {
        actionBar.setTitle("个人信息");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
    }
}
