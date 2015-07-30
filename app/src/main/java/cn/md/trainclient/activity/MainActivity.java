package cn.md.trainclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import cn.md.trainclient.R;
import cn.md.trainclient.activity.coursecenter.CourseCenterActivity;
import cn.md.trainclient.activity.questionbank.QuestionBankActivity;

/**
 * User: su
 * Date: 2015-07-10.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        final View myCourse = findViewById(R.id.my_course);
        final View courseCenter = findViewById(R.id.course_center);
        final View questionBank = findViewById(R.id.question_bank);
        final View examination = findViewById(R.id.examination);
        final View message = findViewById(R.id.message);
        final View personal_center = findViewById(R.id.personal_center);

        myCourse.setOnClickListener(tabOnClickListener);
        courseCenter.setOnClickListener(tabOnClickListener);
        questionBank.setOnClickListener(tabOnClickListener);
        examination.setOnClickListener(tabOnClickListener);
        message.setOnClickListener(tabOnClickListener);
        personal_center.setOnClickListener(tabOnClickListener);
    }

    View.OnClickListener tabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.my_course:
                    startActivity(new Intent(getApplicationContext(), MyCourseActivity.class));
                    break;
                case R.id.course_center:
                    startActivity(new Intent(getApplicationContext(), CourseCenterActivity.class));
                    break;
                case R.id.question_bank:
                    startActivity(new Intent(getApplicationContext(), QuestionBankActivity.class));
                    break;
                case R.id.examination:
                    startActivity(new Intent(getApplicationContext(), ExaminationGuideActivity.class));
                    break;
                case R.id.message:
                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                    break;
                case R.id.personal_center:
                    startActivity(new Intent(getApplicationContext(), PersonalCenterActivity.class));
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.scan).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.scan == item.getItemId()) {
            startActivity(new Intent(getApplicationContext(), ScanActivity.class));
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
