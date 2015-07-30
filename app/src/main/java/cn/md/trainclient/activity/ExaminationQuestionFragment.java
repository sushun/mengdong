package cn.md.trainclient.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import cn.md.trainclient.R;
import cn.md.trainclient.common.IntentExtra;
import cn.md.trainclient.model.ExamAnswer;
import cn.md.trainclient.model.ExamQuestion;

/**
 * User: su
 * Date: 2015-07-12.
 */
public class ExaminationQuestionFragment extends BaseFragmnet {
    private ExamQuestion entity;
    private boolean hasSelected;

    public static ExaminationQuestionFragment newInstance(ExamQuestion entity) {
        ExaminationQuestionFragment fragment = new ExaminationQuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(IntentExtra.OBJ_EXAMINATION_QUESTION_ENTITY, entity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            ExamQuestion examQuestion = (ExamQuestion) bundle.getSerializable(IntentExtra.OBJ_EXAMINATION_QUESTION_ENTITY);
            if (examQuestion != null) {
                entity = examQuestion;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam_question, container, false);

        TextView content = (TextView) view.findViewById(R.id.content);
        final ListView listView = (ListView) view.findViewById(R.id.list_view);

        if (entity != null) {
            content.setText(entity.getContent());
        }

        List<ExamAnswer> answersData = getDummyAnswer();
        listView.setAdapter(new ExamAnswerListAdapter(answersData));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!hasSelected) {
                    View answerView = listView.getChildAt(position).findViewById(R.id.answer_view);
                    answerView.setBackgroundColor(getResources().getColor(R.color.light_gray));

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((ExaminationActivity)getActivity()).nextPage();
                        }
                    }, 400);
                }

                hasSelected = true;
            }
        });

        return view;
    }

    private List<ExamAnswer> getDummyAnswer() {
        List<ExamAnswer> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ExamAnswer entity = new ExamAnswer();
            entity.setContent("测试答案" + i);
            entity.setRight(i == 0 ? true : false);
            data.add(entity);
        }
        return data;
    }

    public class ExamAnswerListAdapter extends BaseAdapter {
        private List<ExamAnswer> data;
        private LayoutInflater layoutInflater;

        public ExamAnswerListAdapter(List<ExamAnswer> data) {
            this.data = data;
            this.layoutInflater = LayoutInflater.from(getActivity());
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
                convertView = layoutInflater.inflate(R.layout.view_list_item_exam_answer, null);
                holder = new ViewHolder();

                holder.label = (ImageView) convertView.findViewById(R.id.answer_label);
                holder.title = (TextView) convertView.findViewById(R.id.title);

                convertView.setTag(holder);
            }

            holder = (ViewHolder) convertView.getTag();
            if (0 == position) {
                holder.label.setImageResource(R.drawable.alpha_a);
            } else if (1 == position) {
                holder.label.setImageResource(R.drawable.alpha_b);
            } else if (2 == position) {
                holder.label.setImageResource(R.drawable.alpha_c);
            } else if (3 == position) {
                holder.label.setImageResource(R.drawable.alpha_d);
            }

            holder.title.setText(data.get(position).getContent());

            return convertView;
        }
    }

    static class ViewHolder {
        ImageView label;
        TextView title;
    }
}
