package cn.md.trainclient.activity.questionbank;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;
import cn.md.trainclient.adapter.ExaminationFragmentAdapter;
import cn.md.trainclient.adapter.QuestionPracticeFragmentAdapter;
import cn.md.trainclient.adapter.SerialNumAdapter;
import cn.md.trainclient.model.ExamQuestion;

/**
 * User: sush
 * Date: 2015-07-29.
 */
public class QuestionPracticeActivity extends BaseActivity {
    private ViewPager viewPager;
    private TextView serialNumHint, collectHint;
    private View serialNumPopView;

    private QuestionPracticeFragmentAdapter adapter;
    private List<ExamQuestion> data;

    private int currentIndex;

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_question_practice);

        initView();
        updateBottomTabView(0);
    }

    private void initView() {
        actionBar.setTitle("题目练习");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        serialNumHint = (TextView) findViewById(R.id.serial_number_hint);
        collectHint = (TextView) findViewById(R.id.collect_hint);
        serialNumPopView = findViewById(R.id.serial_number_pop);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        data = getDummyData();
        adapter = new QuestionPracticeFragmentAdapter(getSupportFragmentManager(), data);
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

        this.findViewById(R.id.serial_number).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serialNumPopView.getVisibility() == View.VISIBLE) {
                    serialNumPopView.setVisibility(View.GONE);
                } else {
                    serialNumPopView.setVisibility(View.VISIBLE);

                    GridView gridView = (GridView) findViewById(R.id.grid_view);
                    gridView.setAdapter(new SerialNumAdapter(getApplicationContext(), data));

                }
            }
        });

        // 下一题
        this.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
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

    private List<ExamQuestion> getDummyData() {
        List<ExamQuestion> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExamQuestion entity = new ExamQuestion();
            entity.setContent("我只是一个很短的测试问题。。" + i);
            data.add(entity);
        }
        return data;
    }

    private void nextQuestion() {
        if (viewPager.getCurrentItem() < adapter.getCount() - 1) {
            viewPager.setCurrentItem(currentIndex + 1, true);
        } else {
            toast("已经是最后一题了哦");
        }
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

    public void nextPage() {
        if (currentIndex < adapter.getCount() - 1) {
            viewPager.setCurrentItem(currentIndex + 1, true);
        }
    }
}
