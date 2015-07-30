package cn.md.trainclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;
import cn.md.trainclient.activity.ExaminationQuestionFragment;
import cn.md.trainclient.model.ExamQuestion;

/**
 * User: su
 * Date: 2015-07-12.
 */
public class ExaminationFragmentAdapter extends FragmentStatePagerAdapter {
    private List<ExamQuestion> data;

    public ExaminationFragmentAdapter(FragmentManager fm, List<ExamQuestion> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return ExaminationQuestionFragment.newInstance(data.get(position));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public List<ExamQuestion> getData() {
        return this.data;
    }
}
