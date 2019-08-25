package com.bpk.mydemopoc.TabAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bpk.mydemopoc.Demo1Fragment;
import com.bpk.mydemopoc.DemoFourFragment;
import com.bpk.mydemopoc.DemoFragment;
import com.bpk.mydemopoc.DemonextFragment;

public class ViewPageAdapter extends FragmentPagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    private boolean enableSwipe;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPageAdapter(FragmentManager fm, int mNumbOfTabsumb) {
        super(fm);

        this.NumbOfTabs = mNumbOfTabsumb;
        init();
    }

    private void init() {
        enableSwipe = false;
    }




    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Demo1Fragment tab1 = new Demo1Fragment();
                return tab1;

            case 1:
                DemonextFragment tab2 = new DemonextFragment();
                return tab2;
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


    public void setPagingEnabled(boolean enabled) {
        this.enableSwipe = enabled;
    }
}