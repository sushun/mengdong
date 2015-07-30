package cn.md.trainclient.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import cn.md.trainclient.R;
import cn.md.trainclient.adapter.MyCourseFragmentAdapter;

/**
 * User: su
 * Date: 2015-07-10.
 */
public class MyCourseActivity extends BaseActivity {
    private View tabUnfinishedHint, tabFinishedHint;
    private int currentIndex;

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_course);
        initView();
    }

    private void initView() {
        actionBar.setTitle("我的课程");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        final TextView tabUnfinished = (TextView) findViewById(R.id.tab_unfinished);
        final TextView tabFinished = (TextView) findViewById(R.id.tab_finished);
        tabUnfinishedHint = findViewById(R.id.tab_unfinished_hint);
        tabFinishedHint = findViewById(R.id.tab_finished_hint);

        MyCourseFragmentAdapter myCourseFragmentAdapter = new MyCourseFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myCourseFragmentAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                if (0 == position) {
                    tabUnfinishedHint.setVisibility(View.VISIBLE);
                    tabFinishedHint.setVisibility(View.INVISIBLE);
                } else if (1 == position) {
                    tabUnfinishedHint.setVisibility(View.INVISIBLE);
                    tabFinishedHint.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabUnfinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = 0;
                viewPager.setCurrentItem(currentIndex);
                updateTabHint();
            }
        });

        tabFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = 1;
                viewPager.setCurrentItem(currentIndex);
                updateTabHint();
            }
        });
    }

    private void updateTabHint() {
        if (0 == currentIndex) {
            tabUnfinishedHint.setVisibility(View.VISIBLE);
            tabFinishedHint.setVisibility(View.INVISIBLE);
        } else if (1 == currentIndex) {
            tabUnfinishedHint.setVisibility(View.INVISIBLE);
            tabFinishedHint.setVisibility(View.VISIBLE);
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
}
