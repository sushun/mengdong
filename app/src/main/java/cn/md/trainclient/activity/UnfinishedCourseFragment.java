package cn.md.trainclient.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import cn.md.trainclient.R;
import cn.md.trainclient.adapter.UnfinishedCourseAdapter;
import cn.md.trainclient.model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * User: su
 * Date: 2015-07-10.
 */
public class UnfinishedCourseFragment extends BaseFragmnet {

    private SwipeRefreshLayout mRefreshLayout;

    public static UnfinishedCourseFragment newInstance() {
        UnfinishedCourseFragment fragment = new UnfinishedCourseFragment();
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

        inItRefreshLayout(view);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                mRefreshLayout.setRefreshing(false);
                                Toast.makeText(inflater.getContext(), "获取数据结束。。。", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }.start();
            }
        });
        return view;
    }

    /**
     * 初始化下拉刷新控件
     * @param v
     */
    private void inItRefreshLayout(View v) {
        mRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refreshLayout);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mRefreshLayout.setProgressViewOffset(true, -100, (int) (64 * metrics.density));
        mRefreshLayout.setColorSchemeResources(R.color.pink_500);
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
