package cn.md.trainclient.activity.questionbank;

import android.content.Intent;
import android.os.Bundle;

import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;

/**
 * User: sush
 * Date: 2015-07-30.
 */
public class ChapterPracticeActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_chapter_practice);

        initView();
    }

    private void initView() {
        actionBar.setTitle("章节练习");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        Intent intent = new Intent(getApplicationContext(), ChapterSelectActivity.class);
        startActivity(intent);
        finish();
    }
}
