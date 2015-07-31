package cn.md.trainclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import cn.md.trainclient.R;
import cn.md.trainclient.model.ExamQuestion;

/**
 * User: su
 * Date: 2015-07-14.
 */
public class SerialNumAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<ExamQuestion> data;

    public SerialNumAdapter(Context context, List<ExamQuestion> data) {
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

    public List<ExamQuestion> getData() {
        return data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.view_grid_view_serial_num, null);
            holder = new ViewHolder();

            holder.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        holder.title.setText(String.valueOf(position));

        return convertView;
    }

    static class ViewHolder {
        TextView title;
    }
}
