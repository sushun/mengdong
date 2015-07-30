package cn.md.trainclient.activity.coursecenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.md.trainclient.R;
import cn.md.trainclient.adapter.UnfinishedCourseAdapter;
import cn.md.trainclient.model.Course;

/**
 * User: sush
 * Date: 2015-07-30.
 */
public class CourseDetailThreeFragment extends Fragment {
    public static CourseDetailThreeFragment newInstance() {
        CourseDetailThreeFragment fragment = new CourseDetailThreeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unfinished_course, container, false);
        UnfinishedCourseAdapter adapter = new UnfinishedCourseAdapter(getActivity(), getDummyData());
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }

    private List<Course> getDummyData() {
        List<Course> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Course entity = new Course();
            entity.setCourseName("courseName " + i);
            data.add(entity);
        }
        return data;
    }
}
