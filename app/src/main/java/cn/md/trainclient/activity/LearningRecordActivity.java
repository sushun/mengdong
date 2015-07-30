package cn.md.trainclient.activity;

import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import cn.md.trainclient.R;
import cn.md.trainclient.adapter.LearningRecordAdapter;
import cn.md.trainclient.model.LearningRecord;

/**
 * User: sush
 * Date: 2015-07-28.
 */
public class LearningRecordActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_learning_record);

        initView();
    }

    private void initView() {
        actionBar.setTitle("学习记录");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new LearningRecordAdapter(getApplicationContext(), getDummyData()));
    }

    private List<LearningRecord> getDummyData() {
        List<LearningRecord> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LearningRecord entity = new LearningRecord();
            entity.setCourseName("course_name" + i);
            entity.setCourseType(i);
            entity.setLearningDate(System.currentTimeMillis());
            entity.setProgress(80);
            data.add(entity);
        }
        return data;
    }
}
