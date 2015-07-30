package cn.md.trainclient.activity.questionbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;

/**
 * 题库
 * User: su
 * Date: 2015-07-10.
 */
public class QuestionBankActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_question_bank);
        
        initView();
    }

    private void initView() {
        actionBar.setTitle("题库");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        View tab1 = findViewById(R.id.tab_1);
        View tab2 = findViewById(R.id.tab_2);
        View tab3 = findViewById(R.id.tab_3);
        View tab4 = findViewById(R.id.tab_4);
        View tab5 = findViewById(R.id.tab_5);
        View tab6 = findViewById(R.id.tab_6);

        tab1.setOnClickListener(onClickListener);
        tab2.setOnClickListener(onClickListener);
        tab3.setOnClickListener(onClickListener);
        tab4.setOnClickListener(onClickListener);
        tab5.setOnClickListener(onClickListener);
        tab6.setOnClickListener(onClickListener);

//        View myWrong = findViewById(R.id.my_wrong);
//        View myCollect = findViewById(R.id.my_collect);
//        myWrong.setOnClickListener(onClickListener);
//        myCollect.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tab_1:
                    startActivity(new Intent(getApplicationContext(), SequencePracticeActivity.class));
                    break;
                case R.id.tab_2:
                    startActivity(new Intent(getApplicationContext(), ChapterPracticeActivity.class));
                    break;
                case R.id.tab_3:
                    startActivity(new Intent(getApplicationContext(), RandomPracticeActivity.class));
                    break;
                case R.id.tab_4:
                    startActivity(new Intent(getApplicationContext(), PracticeStatisticsActivity.class));
                    break;
                case R.id.tab_5:
                    startActivity(new Intent(getApplicationContext(), SimulateExaminationActivity.class));
                    break;
                case R.id.tab_6:
                    startActivity(new Intent(getApplicationContext(), QuestionCollectActivity.class));
                    break;
//                case R.id.my_wrong:
//                     我的错题
//                    startActivity(new Intent(getApplicationContext(), QuestionWrongActivity.class));
//                    break;
//                case R.id.my_collect:
//                     我的收藏
//                    startActivity(new Intent(getApplicationContext(), QuestionCollectActivity.class));
//                    break;
            }
        }
    };
}
