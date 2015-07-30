package cn.md.trainclient.activity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.md.trainclient.R;
import cn.md.trainclient.adapter.TeacherAppraisalAdapter;
import cn.md.trainclient.model.TeacherAppraisal;

/**
 * User: sush
 * Date: 2015-07-28.
 */
public class TeacherAppraisalActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_teacher_appraisal);

        initView();
    }

    private void initView() {
        actionBar.setTitle("教师评价");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new TeacherAppraisalAdapter(getApplicationContext(), getDummyData()));
    }

    private List<TeacherAppraisal> getDummyData() {
        List<TeacherAppraisal> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TeacherAppraisal entity = new TeacherAppraisal();
            entity.setContent("这位同学学习真的很认真，不错，继续努力!" + i);
            entity.setTeacherName("王老师" + i);
            entity.setTime(System.currentTimeMillis());
            data.add(entity);
        }
        return data;
    }
}
