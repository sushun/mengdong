package cn.md.trainclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.md.trainclient.R;

/**
 * User: su
 * Date: 2015-07-10.
 */
public class PersonalCenterActivity extends BaseActivity {

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal_center);

        initView();
    }

    private void initView() {
        actionBar.setTitle("个人中心");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        View personalInfo = findViewById(R.id.personal_info);
        View learningRecord = findViewById(R.id.learning_record);
        View teacherAppraisal = findViewById(R.id.teacher_appraisal);
        View examinationRecord = findViewById(R.id.examination_record);

        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PersonalInfoActivity.class));
            }
        });

        learningRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LearningRecordActivity.class));
            }
        });

        teacherAppraisal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TeacherAppraisalActivity.class));
            }
        });

        examinationRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ExaminationRecordActivity.class));
            }
        });
    }
}
