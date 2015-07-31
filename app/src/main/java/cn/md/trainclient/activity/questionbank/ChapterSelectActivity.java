package cn.md.trainclient.activity.questionbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;
import cn.md.trainclient.adapter.CourseChapterAdapter;
import cn.md.trainclient.model.CourseChapter;

/**
 * User: sush
 * Date: 2015-07-31.
 */
public class ChapterSelectActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_chapter_select);

        initView();
    }

    private void initView() {
        ListView listView = (ListView) findViewById(R.id.list_view);
        CourseChapterAdapter adapter = new CourseChapterAdapter(getApplicationContext(), getDummyData());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), QuestionPracticeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private List<CourseChapter> getDummyData() {
        List<CourseChapter> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CourseChapter entity = new CourseChapter();
            entity.setCourseName("章节名" + i);
            entity.setQuestionNum(i * 5);
            data.add(entity);
        }
        return data;
    }
}
