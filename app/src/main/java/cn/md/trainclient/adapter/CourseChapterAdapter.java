package cn.md.trainclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.md.trainclient.R;
import cn.md.trainclient.model.CourseChapter;
import cn.md.trainclient.model.Message;

/**
 * User: su
 * Date: 2015-07-14.
 */
public class CourseChapterAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<CourseChapter> data;

    public CourseChapterAdapter(Context context, List<CourseChapter> data) {
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

    public List<CourseChapter> getData() {
        return data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.view_list_item_course_chapter, null);
            holder = new ViewHolder();

            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.subTitle = (TextView) convertView.findViewById(R.id.subtitle);

            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        final CourseChapter entity = data.get(position);
        holder.title.setText(entity.getCourseName());
        holder.subTitle.setText(String.valueOf(entity.getQuestionNum()));

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView subTitle;
    }
}
