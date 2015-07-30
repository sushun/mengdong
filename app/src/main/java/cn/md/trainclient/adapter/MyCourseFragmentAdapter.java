package cn.md.trainclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cn.md.trainclient.activity.FinishedCourseFragment;
import cn.md.trainclient.activity.UnfinishedCourseFragment;

/**
 * User: su
 * Date: 2015-07-10.
 */
public class MyCourseFragmentAdapter extends FragmentPagerAdapter {

    public MyCourseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (0 == position) {
            return UnfinishedCourseFragment.newInstance();
        } else if (1 == position) {
            return FinishedCourseFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
