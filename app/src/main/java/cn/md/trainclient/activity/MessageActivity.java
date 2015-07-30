package cn.md.trainclient.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.widget.ListView;
import android.widget.Toast;
import cn.md.trainclient.R;
import cn.md.trainclient.adapter.MessageAdapter;
import cn.md.trainclient.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * User: su
 * Date: 2015-07-10.
 */
public class MessageActivity extends BaseActivity {
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_message);

        initView();
    }

    private void initView() {
        actionBar.setTitle("消息咨询");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        ListView listView = (ListView) findViewById(R.id.list_view);

        MessageAdapter adapter = new MessageAdapter(getApplicationContext(), getDummyData());
        listView.setAdapter(adapter);
        inItRefreshLayout();
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
                                Toast.makeText(getApplicationContext(), "获取数据结束。。。", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }.start();
            }
        });
    }

    /**
     * 初始化下拉刷新控件
     */
    private void inItRefreshLayout() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mRefreshLayout.setProgressViewOffset(true, -100, (int) (64 * metrics.density));
        mRefreshLayout.setColorSchemeResources(R.color.pink_500);
    }

    private List<Message> getDummyData() {
        List<Message> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message entity = new Message();
            entity.setContent("测试消息内容" + i);
            entity.setSenderName("王晓明" + i);
            data.add(entity);
        }
        return data;
    }
}
