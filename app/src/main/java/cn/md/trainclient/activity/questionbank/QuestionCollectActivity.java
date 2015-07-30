package cn.md.trainclient.activity.questionbank;

import android.os.Bundle;

import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;

/**
 * User: su
 * Date: 2015-07-14.
 */
public class QuestionCollectActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_question_collect);

        initView();
    }

    private void initView() {
        actionBar.setTitle("我的收藏");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
    }
}
