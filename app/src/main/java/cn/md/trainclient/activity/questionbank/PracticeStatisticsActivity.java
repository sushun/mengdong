package cn.md.trainclient.activity.questionbank;

import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;

/**
 * 练习统计
 * User: sush
 * Date: 2015-07-30.
 */
public class PracticeStatisticsActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_practice_statistics);

        initView();
    }

    private void initView() {
        actionBar.setTitle("练习统计");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        PieGraph pg = (PieGraph) findViewById(R.id.graph);
        PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor("#99CC00"));
        slice.setValue(2);
        slice.setTitle("答错题");
        pg.addSlice(slice);

        slice = new PieSlice();
        slice.setColor(Color.parseColor("#FFBB33"));
        slice.setValue(2);
        slice.setTitle("未做题");
        pg.addSlice(slice);

        slice = new PieSlice();
        slice.setColor(getResources().getColor(R.color.primary));
        slice.setValue(8);
        slice.setTitle("答对题");
        pg.addSlice(slice);

//        for (PieSlice s : pg.getSlices())
//            s.setGoalValue((float) Math.random() * 10);
//        pg.setDuration(1000);//default if unspecified is 300 ms
//        pg.setInterpolator(new AccelerateDecelerateInterpolator());//default if unspecified is linear; constant speed
////        pg.setAnimationListener(getAnimationListener());//optional
//        pg.animateToGoalValues();
    }
}
