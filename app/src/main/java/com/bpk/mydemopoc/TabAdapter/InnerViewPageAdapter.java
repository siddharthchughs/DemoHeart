package com.bpk.mydemopoc.TabAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bpk.mydemopoc.Demo1Fragment;
import com.bpk.mydemopoc.DemoFourFragment;
import com.bpk.mydemopoc.DemoFragment;
import com.bpk.mydemopoc.DemonextFragment;

public class InnerViewPageAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public InnerViewPageAdapter(FragmentManager fm, int mNumbOfTabsumb) {
        super(fm);

//        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
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





    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}