package com.bpk.mydemopoc;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.bpk.mydemopoc.TabAdapter.PageAdapter;
import com.bpk.mydemopoc.TabAdapter.SlidingTabLayout;
import com.bpk.mydemopoc.TabAdapter.ViewPageAdapter;

public class HeartActivity extends AppCompatActivity {

    private ViewPager pager;
    TabLayout tabLayout ;
    ViewPageAdapter adapter;
//    PageAdapter fragmentAdapter;
    CharSequence Titles[]={"H","D","W","M"};
    int Numboftabs =4;
    ConstraintLayout cl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout1);
         tabLayout.addTab(tabLayout.newTab().setText("H"));
        tabLayout.addTab(tabLayout.newTab().setText("D"));
        tabLayout.addTab(tabLayout.newTab().setText("W"));
        tabLayout.addTab(tabLayout.newTab().setText("M"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pager =  (ViewPager) findViewById(R.id.pager1);

        adapter =  new ViewPageAdapter(getSupportFragmentManager(),Numboftabs);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab LayoutTab) {

                pager.setCurrentItem(LayoutTab.getPosition());



            }

            @Override
            public void onTabUnselected(TabLayout.Tab LayoutTab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab LayoutTab) {

            }
        });
    }

}
