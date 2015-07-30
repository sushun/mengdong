package cn.md.trainclient.activity.questionbank;

import android.os.Bundle;

import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;

/**
 * 顺序练习
 * User: sush
 * Date: 2015-07-30.
 */
public class SequencePracticeActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sequence_practice);

        initView();
    }

    private void initView() {
        actionBar.setTitle("顺序练习");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
    }
}
