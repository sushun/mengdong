package cn.md.trainclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import cn.md.trainclient.R;
import cn.md.trainclient.model.Message;
import cn.md.trainclient.model.TeacherAppraisal;
import cn.md.trainclient.utils.TimeUtil;

/**
 * User: su
 * Date: 2015-07-14.
 */
public class TeacherAppraisalAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<TeacherAppraisal> data;

    public TeacherAppraisalAdapter(Context context, List<TeacherAppraisal> data) {
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

    public List<TeacherAppraisal> getData() {
        return data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.view_list_item_teacher_appraisal, null);
            holder = new ViewHolder();

            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.subTitle = (TextView) convertView.findViewById(R.id.subtitle);
            holder.time = (TextView) convertView.findViewById(R.id.time);

            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        final TeacherAppraisal entity = data.get(position);
        holder.title.setText(entity.getTeacherName());
        holder.subTitle.setText(entity.getContent());
        holder.time.setText(TimeUtil.friendly_time(new Date(entity.getTime())));

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView subTitle;
        TextView time;
    }
}
