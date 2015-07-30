package cn.md.trainclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.md.trainclient.R;
import cn.md.trainclient.model.Course;

/**
 * User: su
 * Date: 2015-07-10.
 */
public class FinishedCourseAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Course> data;

    public FinishedCourseAdapter(Context context, List<Course> data) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.view_list_item_unfinished_course, null);
            holder = new ViewHolder();

            holder.courseName = (TextView) convertView.findViewById(R.id.course_name);

            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        holder.courseName.setText(data.get(position).getCourseName());

        return convertView;
    }

    static class ViewHolder {
        TextView courseName;
    }
}
