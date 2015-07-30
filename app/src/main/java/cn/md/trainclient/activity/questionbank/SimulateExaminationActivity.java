package cn.md.trainclient.activity.questionbank;

import android.content.Intent;
import android.os.Bundle;

import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;
import cn.md.trainclient.activity.ExaminationActivity;

/**
 * 模拟考试
 * User: sush
 * Date: 2015-07-30.
 */
public class SimulateExaminationActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sequence_practice);

        initView();
    }

    private void initView() {
        actionBar.setTitle("模拟考试");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        Intent intent = new Intent(getApplicationContext(), ExaminationActivity.class);
        startActivity(intent);
        finish();
    }
}
