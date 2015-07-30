package cn.md.trainclient.activity.coursecenter;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;
import cn.md.trainclient.adapter.CourseCenterAdapter;

/**
 * User: su
 * Date: 2015-07-10.
 */
public class CourseCenterActivity extends BaseActivity {
    private ViewPager viewPager;
    private View tabOneHint, tabTwoHint, tabThreeHint, tabFourHint;
    private int currentIndex;

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_course_center);
        initView();
    }

    private void initView() {
        actionBar.setTitle("课程中心");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        final TextView tabOne = (TextView) findViewById(R.id.tab_one);
        final TextView tabTwo = (TextView) findViewById(R.id.tab_two);
        final TextView tabThree = (TextView) findViewById(R.id.tab_three);
        final TextView tabFour = (TextView) findViewById(R.id.tab_four);
        tabOneHint = findViewById(R.id.tab_one_hint);
        tabTwoHint = findViewById(R.id.tab_two_hint);
        tabThreeHint = findViewById(R.id.tab_three_hint);
        tabFourHint = findViewById(R.id.tab_four_hint);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        CourseCenterAdapter adapter = new CourseCenterAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                updateTabHint();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = 0;
                updateTabHint();
            }
        });

        tabTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = 1;
                updateTabHint();
            }
        });

        tabThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = 2;
                updateTabHint();
            }
        });

        tabFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = 3;
                updateTabHint();
            }
        });
    }

    private void updateTabHint() {
        if (0 == currentIndex) {
            viewPager.setCurrentItem(0);
            tabOneHint.setVisibility(View.VISIBLE);
            tabTwoHint.setVisibility(View.INVISIBLE);
            tabThreeHint.setVisibility(View.INVISIBLE);
            tabFourHint.setVisibility(View.INVISIBLE);
        } else if (1 == currentIndex) {
            viewPager.setCurrentItem(1);
            tabOneHint.setVisibility(View.INVISIBLE);
            tabTwoHint.setVisibility(View.VISIBLE);
            tabThreeHint.setVisibility(View.INVISIBLE);
            tabFourHint.setVisibility(View.INVISIBLE);
        } else if (2 == currentIndex) {
            viewPager.setCurrentItem(2);
            tabOneHint.setVisibility(View.INVISIBLE);
            tabTwoHint.setVisibility(View.INVISIBLE);
            tabThreeHint.setVisibility(View.VISIBLE);
            tabFourHint.setVisibility(View.INVISIBLE);
        } else {
            viewPager.setCurrentItem(3);
            tabOneHint.setVisibility(View.INVISIBLE);
            tabTwoHint.setVisibility(View.INVISIBLE);
            tabThreeHint.setVisibility(View.INVISIBLE);
            tabFourHint.setVisibility(View.VISIBLE);
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
