package cn.md.trainclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.md.trainclient.R;
import cn.md.trainclient.model.ExamRecord;
import cn.md.trainclient.model.LearningRecord;

/**
 * User: su
 * Date: 2015-07-14.
 */
public class LearningRecordAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<LearningRecord> data;

    public LearningRecordAdapter(Context context, List<LearningRecord> data) {
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

    public List<LearningRecord> getData() {
        return data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.view_list_item_learning_record, null);
            holder = new ViewHolder();

            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.title_2 = (TextView) convertView.findViewById(R.id.title_2);
            holder.title_3 = (TextView) convertView.findViewById(R.id.title_3);
            holder.title_4 = (TextView) convertView.findViewById(R.id.title_4);

            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        final LearningRecord entity = data.get(position);
        holder.title.setText(entity.getCourseName());
        holder.title_2.setText(String.valueOf(entity.getCourseType()));
        holder.title_3.setText(String.valueOf(entity.getProgress()));
        holder.title_4.setText(String.valueOf(entity.getLearningDate()));

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView title_2;
        TextView title_3;
        TextView title_4;
    }
}
