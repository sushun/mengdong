package cn.md.trainclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.md.trainclient.R;
import cn.md.trainclient.model.Course;
import cn.md.trainclient.utils.TimeUtil;

import java.util.List;

/**
 * User: su
 * Date: 2015-07-13.
 */
public class CourseCenterTabOneAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Course> data;

    public CourseCenterTabOneAdapter(Context context, List<Course> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Course> getData() {
        return data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.view_list_item_course_center_tab_one, null);
            holder = new ViewHolder();
            holder.courseName = (TextView) convertView.findViewById(R.id.course_name);
            holder.course_describe = (TextView) convertView.findViewById(R.id.course_describe);
            holder.course_createTime = (TextView) convertView.findViewById(R.id.course_createtime);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.courseName.setText(data.get(position).getCourseName());
        holder.course_describe.setText(data.get(position).getCourseDescribe());
//        holder.course_createTime.setText(TimeUtil.friendly_time(data.get(position).getCourseCreateTime()));
        return convertView;
    }

    static class ViewHolder {
        TextView courseName;
        TextView course_describe;
        TextView course_createTime;
    }
}
