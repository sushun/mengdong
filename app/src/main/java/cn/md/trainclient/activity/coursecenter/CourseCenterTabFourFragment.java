package cn.md.trainclient.activity.coursecenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseFragmnet;
import cn.md.trainclient.adapter.CourseCenterTabFourAdapter;
import cn.md.trainclient.api.CourseQuerryRequest;
import cn.md.trainclient.api.CourseQuerryResponse;
import cn.md.trainclient.common.IntentExtra;
import cn.md.trainclient.model.Course;
import cn.md.trainclient.request.OnRequestResponseListener;
import cn.md.trainclient.request.Request;
import cn.md.trainclient.request.RequestManager;
import cn.md.trainclient.response.SuccessResult;
import cn.md.trainclient.view.RefreshLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * User: su
 * Date: 2015-07-13.
 */
public class CourseCenterTabFourFragment extends BaseFragmnet {
    private RefreshLayout mRefreshLayout;
    private CourseCenterTabFourAdapter adapter;
    private List<Course> data;
    private ListView listView;
    private final int pagesize = 10;
    private int pageindex = 1;

    public static CourseCenterTabFourFragment newInstance() {
        CourseCenterTabFourFragment fragment = new CourseCenterTabFourFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_tab_four, container, false);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new CourseCenterTabFourAdapter(getActivity().getApplicationContext(), getDummyData());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CourseDetailActivity.class);
                intent.putExtra(IntentExtra.OBJ_COURSE, adapter.getData().get(position));
                startActivity(intent);
            }
        });
        inItRefreshLayout(view);
        return view;
    }

    private void inItRefreshLayout(final View v) {
        mRefreshLayout = (RefreshLayout) v.findViewById(R.id.refreshLayout);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mRefreshLayout.setProgressViewOffset(true, -100, (int) (64 * metrics.density));
        mRefreshLayout.setColorSchemeResources(R.color.pink_500);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CourseQuerryRequest request = new CourseQuerryRequest(4, 1, pagesize);
                RequestManager.getInstance().startRequest(new OnRequestResponseListener() {
                    @Override
                    public void onRequestSuccess(long requestId, Request request, final SuccessResult successResult) {
                        pageindex = 2;
                        CourseQuerryResponse response = (CourseQuerryResponse) successResult.getResponse();
                        if (response == null) {
                            snackErr(v.findViewById(R.id.container), "网路连接失败");
                        } else {
                            data = response.getData();
                            adapter = new CourseCenterTabFourAdapter(getActivity(), data);
                            listView.setAdapter(adapter);
                            snackShow(v.findViewById(R.id.container), "获取数据" + response.getData().size() + "条");
                        }
                        mRefreshLayout.setRefreshing(false);
                    }
                }, request);
            }
        });
        mRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                CourseQuerryRequest request = new CourseQuerryRequest(4, pageindex, pagesize);
                pageindex++;
                RequestManager.getInstance().startRequest(new OnRequestResponseListener() {
                    @Override
                    public void onRequestSuccess(long requestId, Request request, final SuccessResult successResult) {
                        CourseQuerryResponse response = (CourseQuerryResponse) successResult.getResponse();
                        if (response == null) {
                            snackErr(v.findViewById(R.id.container), "网路连接失败");
                        } else {
                            data.addAll(response.getData());
                            adapter.notifyDataSetChanged();
                            snackShow(v.findViewById(R.id.container), "获取数据" + response.getData().size() + "条");
                        }
                        mRefreshLayout.setLoading(false);
                    }
                }, request);
            }
        });
    }

    private List<Course> getDummyData() {
        List<Course> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Course entity = new Course();
            entity.setCourseName("课程名称" + i);
            data.add(entity);
        }
        return data;
    }
}
