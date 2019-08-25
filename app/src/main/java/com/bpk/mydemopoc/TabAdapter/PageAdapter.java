package com.bpk.mydemopoc.TabAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bpk.mydemopoc.Demo1Fragment;
import com.bpk.mydemopoc.DemoFourFragment;
import com.bpk.mydemopoc.DemoFragment;
import com.bpk.mydemopoc.DemonextFragment;


/**
 * Created by JUNED on 5/30/2016.
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    int TabCount;
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    public PageAdapter(FragmentManager fragmentManager, String mTitles[], int mNumbOfTabsumb) {

        super(fragmentManager);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;


    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                DemoFragment tab1 = new DemoFragment();
                return tab1;

            case 1:
                DemonextFragment tab2 = new DemonextFragment();
                return tab2;
            case 2:
                Demo1Fragment tab3 = new Demo1Fragment();
                return tab3;
            case 3:
                DemoFourFragment tab4 = new DemoFourFragment();
                return tab4;


             default:
                return null;
        }
    }


    @Override

    public CharSequence getPageTitle(int position) {
       return Titles[position];
    }





    @Override
    public int getCount() {
        return TabCount;
    }
}