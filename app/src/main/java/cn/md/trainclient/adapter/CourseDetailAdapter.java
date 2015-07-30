package cn.md.trainclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.md.trainclient.R;
import cn.md.trainclient.model.CoursewareQuerryItem;
import cn.md.trainclient.utils.Loger;

import java.util.List;

/**
 * Created by jindongping on 15/7/24.
 */
public class CourseDetailAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<CoursewareQuerryItem> data;

    public CourseDetailAdapter(Context context, List<CoursewareQuerryItem> data) {
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

    public List<CoursewareQuerryItem> getData() {
        return data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.view_list_item_course_detail, null);
            holder = new ViewHolder();
            Loger.v(data.size());
            holder.courseName = (TextView) convertView.findViewById(R.id.course_ware_name);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.courseName.setText(data.get(position).getCoursewareName());
        return convertView;
    }

    static class ViewHolder {
        TextView courseName;
//        TextView course_describe;
//        TextView course_createTime;
    }
}
