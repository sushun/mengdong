package cn.md.trainclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cn.md.trainclient.R;
import cn.md.trainclient.adapter.ExaminationFragmentAdapter;
import cn.md.trainclient.common.IntentExtra;
import cn.md.trainclient.dialog.ExaminationSubmitDialog;
import cn.md.trainclient.model.ExamQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * User: su
 * Date: 2015-07-10.
 */
public class ExaminationActivity extends BaseActivity {
    private ViewPager viewPager;
    private TextView serialNumHint, errorCountHint, collectHint;
    private MenuItem countDownMenu;

    private long startExamTime, examTime, exceptedTime;
    private int currentIndex;

    private ExaminationFragmentAdapter adapter;

    public static final int REQUEST_CODE_SUBMIT = 0x100;

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_exam);
        initData();
        initView();
    }

    private void initData() {
        this.currentIndex = 0;
        this.startExamTime = System.currentTimeMillis();
        Intent intent = getIntent();
        if (intent != null) {
            int count = intent.getIntExtra(IntentExtra.INT_EXAMINATION_EXAM_TIME, 30);
            this.examTime = count * 60 * 1000;
            this.exceptedTime = startExamTime + examTime;

        }
    }

    private void initView() {
        actionBar.setTitle("考试");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        serialNumHint = (TextView) findViewById(R.id.serial_number_hint);
        errorCountHint = (TextView) findViewById(R.id.collect_hint);
        collectHint = (TextView) findViewById(R.id.collect_hint);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        adapter = new ExaminationFragmentAdapter(getSupportFragmentManager(), getDummyData());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                updateBottomTabView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 交卷
        this.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExaminationSubmitDialog dialog = new ExaminationSubmitDialog();
                dialog.setTargetFragment(dialog, REQUEST_CODE_SUBMIT);
                dialog.show(getFragmentManager(), "examination_submit_dialog");
            }
        });

        // 收藏
        this.findViewById(R.id.collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectQuestion();
            }

        });
    }

    private void collectQuestion() {
        ExamQuestion entity = adapter.getData().get(currentIndex);
        if (entity != null) {
            if (entity.isCollected()) {
                // 已收藏
                adapter.getData().get(currentIndex).setCollected(false);
                Toast.makeText(getApplicationContext(), "取消收藏", Toast.LENGTH_SHORT).show();
                collectHint.setText(R.string.collect_question);
            } else {
                adapter.getData().get(currentIndex).setCollected(true);
                collectHint.setText(R.string.collected);
                Toast.makeText(getApplicationContext(), "已收藏", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateBottomTabView(int position) {
        int totalCount = adapter.getCount();
        if (totalCount > 0 && position < totalCount) {
            serialNumHint.setText((position + 1) + "/" + totalCount);

            if (adapter.getData().get(position).isCollected()) {
                collectHint.setText(R.string.collected);
            } else {
                collectHint.setText(R.string.collect_question);
            }
        }
    }

    private List<ExamQuestion> getDummyData() {
        List<ExamQuestion> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExamQuestion entity = new ExamQuestion();
            entity.setContent("我只是一个很短的测试问题。" + i);
            data.add(entity);
        }
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_examination, menu);
        menu.findItem(R.id.countdown).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        this.countDownMenu = menu.findItem(R.id.countdown);

        CountDownTimer countDownTimer = new CountDownTimer(examTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                long currentTime = System.currentTimeMillis();
                if (currentTime < exceptedTime) {
                    int minute = (int) (millisUntilFinished / 1000 / 60);
                    int second = (int) (millisUntilFinished / 1000 - minute * 60);
                    countDownMenu.setTitle(minute + ":" + second);
                } else {

                }
            }

            @Override
            public void onFinish() {
                // 交卷时间到
            }
        };
        countDownTimer.start();

        return true;
    }

    public void nextPage() {
        if (currentIndex < adapter.getCount() - 1) {
            viewPager.setCurrentItem(currentIndex + 1, true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SUBMIT) {
            if (resultCode == Activity.RESULT_OK) {
                // 确认提交
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
