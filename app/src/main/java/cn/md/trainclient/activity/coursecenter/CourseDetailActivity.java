package cn.md.trainclient.activity.coursecenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import cn.md.trainclient.R;
import cn.md.trainclient.activity.BaseActivity;
import cn.md.trainclient.activity.ScanActivity;
import cn.md.trainclient.adapter.CourseDetailAdapter;
import cn.md.trainclient.adapter.CourseDetailFragmentAdapter;
import cn.md.trainclient.api.CourseWareQuerryRequest;
import cn.md.trainclient.api.CourseWareQuerryResponse;
import cn.md.trainclient.common.IntentExtra;
import cn.md.trainclient.model.Course;
import cn.md.trainclient.model.CoursewareQuerryItem;
import cn.md.trainclient.request.OnRequestResponseListener;
import cn.md.trainclient.request.Request;
import cn.md.trainclient.request.RequestManager;
import cn.md.trainclient.response.SuccessResult;
import cn.md.trainclient.utils.TimeUtil;
import cn.md.trainclient.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * User: su
 * Date: 2015-07-13.
 */
public class CourseDetailActivity extends BaseActivity {
    private TextView courseName, course_describe, course_createTime;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MenuItem collectMenu;

    private Course item;
    private List<CoursewareQuerryItem> data;

    private RefreshLayout mRefreshLayout;
    private ListView listView;
    private CourseDetailAdapter adapter;

    private final int pagesize = 10;
    private int pageindex = 1;

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_course_detail);
        initView();
//        initData();
    }

    private void initData() {
        item = (Course) getIntent().getSerializableExtra(IntentExtra.OBJ_COURSE);
        course_createTime = (TextView) findViewById(R.id.course_createtime);
        courseName.setText(item.getCourseName());
        course_describe.setText(item.getCourseDescribe());
        course_createTime.setText(TimeUtil.friendly_time(item.getCourseCreateTime()));

//        inItRefreshLayout();
    }

    private void initView() {
        actionBar.setTitle("课程详情");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        courseName = (TextView) findViewById(R.id.course_name);
        course_describe = (TextView) findViewById(R.id.course_describe);
        listView = (ListView) findViewById(R.id.list_view);

        List<String> titles = new ArrayList<>();
        titles.add("课件");
        titles.add("图文");
        titles.add("交互");
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
        CourseDetailFragmentAdapter adapter = new CourseDetailFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    private void inItRefreshLayout() {
        mRefreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mRefreshLayout.setProgressViewOffset(true, -100, (int) (64 * metrics.density));
        mRefreshLayout.setColorSchemeResources(R.color.pink_500);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CourseWareQuerryRequest request = new CourseWareQuerryRequest(1, 1, pagesize);
                RequestManager.getInstance().startRequest(new OnRequestResponseListener() {
                    @Override
                    public void onRequestSuccess(long requestId, Request request, final SuccessResult successResult) {
                        pageindex = 2;
                        CourseWareQuerryResponse response = (CourseWareQuerryResponse) successResult.getResponse();
                        if (response == null) {
                            snackErr(findViewById(R.id.container), "网路连接失败");
                        } else {
                            data = response.getData();
                            adapter = new CourseDetailAdapter(getApplicationContext(), data);
                            listView.setAdapter(adapter);
                            snackShow(findViewById(R.id.container), "获取数据" + response.getData().size() + "条");
                        }
                        mRefreshLayout.setRefreshing(false);
                    }
                }, request);
            }
        });
        mRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                CourseWareQuerryRequest request = new CourseWareQuerryRequest(1, pageindex, pagesize);
                pageindex++;
                RequestManager.getInstance().startRequest(new OnRequestResponseListener() {
                    @Override
                    public void onRequestSuccess(long requestId, Request request, final SuccessResult successResult) {
                        CourseWareQuerryResponse response = (CourseWareQuerryResponse) successResult.getResponse();
                        if (response == null) {
                            snackErr(findViewById(R.id.container), "网路连接失败");
                        } else {
                            data.addAll(response.getData());
                            adapter.notifyDataSetChanged();
                            snackShow(findViewById(R.id.container), "获取数据" + response.getData().size() + "条");
                        }
                        mRefreshLayout.setLoading(false);
                    }
                }, request);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_detail, menu);
        collectMenu = menu.findItem(R.id.collect);
        collectMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.collect == item.getItemId()) {
            updateCollectMenu();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void updateCollectMenu() {
        if (collectMenu == null) {
            return ;
        }
        if (collectMenu.getTitle().equals("收藏")) {
            toast("收藏成功");
            collectMenu.setTitle("取消收藏");
        } else {
            toast("取消收藏");
            collectMenu.setTitle("收藏");
        }
    }
}
