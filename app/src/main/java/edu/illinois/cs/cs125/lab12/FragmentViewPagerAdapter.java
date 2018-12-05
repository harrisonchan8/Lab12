package edu.illinois.cs.cs125.lab12;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.TextView;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragmentPages;

    FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentPages = new Fragment[] {
                new ApplianceSelection(),
                new InputApplianceStats(),
        };
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentPages[position];
    }

    @Override
    public int getCount() {
        return fragmentPages.length;
//        return 1;
    }
}
