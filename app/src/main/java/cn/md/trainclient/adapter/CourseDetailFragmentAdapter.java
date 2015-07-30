package cn.md.trainclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cn.md.trainclient.activity.coursecenter.CourseDetailOneFragment;
import cn.md.trainclient.activity.coursecenter.CourseDetailThreeFragment;
import cn.md.trainclient.activity.coursecenter.CourseDetailTwoFragment;

/**
 * User: su
 * Date: 2015-07-13.
 */
public class CourseDetailFragmentAdapter extends FragmentPagerAdapter {

    public static final String [] titles = new String[]{"课件", "图文", "交互"};

    public CourseDetailFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (0 == position) {
            return CourseDetailOneFragment.newInstance();
        } else if (1 == position) {
            return CourseDetailTwoFragment.newInstance();
        } else {
            return CourseDetailThreeFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
