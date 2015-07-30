package cn.md.trainclient.activity.questionbank;

import android.os.Bundle;
import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;

public class RandomPracticeActivity extends BaseActivity {

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_random_practice);

        initView();
    }

    private void initView() {
        actionBar.setTitle("随机练习");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
    }

}
