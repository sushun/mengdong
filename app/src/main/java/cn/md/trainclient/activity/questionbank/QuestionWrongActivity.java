package cn.md.trainclient.activity.questionbank;

import android.os.Bundle;

import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;

/**
 * User: su
 * Date: 2015-07-14.
 */
public class QuestionWrongActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_question_wrong);

        initView();
    }

    private void initView() {
        actionBar.setTitle("我的错题");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
    }
}
