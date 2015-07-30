package cn.md.trainclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cn.md.trainclient.activity.coursecenter.CourseCenterTabFourFragment;
import cn.md.trainclient.activity.coursecenter.CourseCenterTabOneFragment;
import cn.md.trainclient.activity.coursecenter.CourseCenterTabThreeFragment;
import cn.md.trainclient.activity.coursecenter.CourseCenterTabTwoFragment;

/**
 * User: su
 * Date: 2015-07-13.
 */
public class CourseCenterAdapter extends FragmentPagerAdapter {

    public CourseCenterAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (0 == position) {
            return CourseCenterTabOneFragment.newInstance();
        } else if (1 == position) {
            return CourseCenterTabTwoFragment.newInstance();
        } else if (2 == position) {
            return CourseCenterTabThreeFragment.newInstance();
        } else {
            return CourseCenterTabFourFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
