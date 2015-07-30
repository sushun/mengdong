package cn.md.trainclient.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ListView;

import com.db.chart.Tools;
import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.LineChartView;
import com.db.chart.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

import cn.md.trainclient.R;
import cn.md.trainclient.adapter.ExaminationRecordAdapter;
import cn.md.trainclient.model.ExamRecord;

/**
 * User: sush
 * Date: 2015-07-28.
 */
public class ExaminationRecordActivity extends BaseActivity {
    private LineChartView mChartOne;

    private final String[] mLabelsOne = {"", "1-1", "", "2-2", "", "3-3", "", "4-4", "", "5-5", ""};
    private final float[][] mValuesOne = {{3.5f, 4.7f, 4.3f, 8f, 6.5f, 10f, 7f, 8.3f, 7.0f, 7.3f, 5f},
            {2.5f, 3.5f, 3.5f, 7f, 5.5f, 8.5f, 6f, 6.3f, 5.8f, 6.3f, 4.5f},
            {1.5f, 2.5f, 2.5f, 4f, 2.5f, 5.5f, 5f, 5.3f, 4.8f, 5.3f, 3f}};

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_examination_record);

        initView();
    }

    private void initView() {
        actionBar.setTitle("考试记录");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        mChartOne = (LineChartView) findViewById(R.id.line_chart);

        produceOne(mChartOne, action);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ExaminationRecordAdapter(getApplicationContext(), getDummyData()));
    }

    private List<ExamRecord> getDummyData() {
        List<ExamRecord> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExamRecord entity = new ExamRecord();
            entity.setCourseName("course_name_" + i);
            entity.setDuration(1000 * i);
            entity.setDate(System.currentTimeMillis());
            data.add(entity);
        }
        return data;
    }

    /**
     * Chart 1
     */
    public void produceOne(LineChartView chart, Runnable action) {
        LineSet dataset = new LineSet(mLabelsOne, mValuesOne[0]);
        dataset.setColor(Color.parseColor("#a34545"))
                .setFill(Color.parseColor("#a34545"))
                .setSmooth(true);
        chart.addData(dataset);

        dataset = new LineSet(mLabelsOne, mValuesOne[1]);
        dataset.setColor(Color.parseColor("#e08b36"))
                .setFill(Color.parseColor("#e08b36"))
                .setSmooth(true);
        chart.addData(dataset);

        dataset = new LineSet(mLabelsOne, mValuesOne[2]);
        dataset.setColor(Color.parseColor("#61263c"))
                .setFill(Color.parseColor("#61263c"))
                .setSmooth(true);
        chart.addData(dataset);

        chart.setTopSpacing(Tools.fromDpToPx(15))
                .setBorderSpacing(Tools.fromDpToPx(0))
                .setAxisBorderValues(0, 10, 1)
                .setXLabels(AxisController.LabelPosition.INSIDE)
                .setYLabels(AxisController.LabelPosition.NONE)
                .setLabelsColor(Color.parseColor("#e08b36"))
                .setXAxis(false)
                .setYAxis(false);

        Animation anim = new Animation().setStartPoint(-1, 1).setEndAction(action);

        chart.show(anim);
    }

    Runnable action = new Runnable() {
        @Override
        public void run() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                }
            }, 500);
        }
    };

    public void updateOne(LineChartView chart) {
        float[][] newValues = {{3.5f, 4.7f, 4.3f, 8f, 6.5f, 10f, 7f, 8.3f, 7.0f, 7.3f, 5f},
                {1f, 2f, 2f, 3.5f, 2f, 5f, 4.5f, 4.8f, 4.3f, 4.8f, 2.5f}};
        chart.updateValues(1, newValues[0]);
        chart.updateValues(2, newValues[1]);
        chart.notifyDataUpdate();
    }


    public static void dismissOne(LineChartView chart, Runnable action) {
        chart.dismiss(new Animation().setStartPoint(-1, 0).setEndAction(action));
    }
}
