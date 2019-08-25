package com.bpk.mydemopoc;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bpk.mydemopoc.TabAdapter.CustomViewPager;
import com.bpk.mydemopoc.TabAdapter.PageAdapter;
import com.bpk.mydemopoc.TabAdapter.SlidingTabLayout;
import com.bpk.mydemopoc.TabAdapter.ViewPageAdapter;

public class DemoSlidingActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager pager;
//    private CustomViewPager pager;
    private CustomViewPager customPager;
    TabLayout tabLayout;
    PageAdapter adapter;
    ViewPageAdapter viewPageAdapter;
//    String Titles[] = {"Home", "Events"};
    int Numboftabs = 2;
    private ImageView img_Heart;
    private ImageView img_Brain;
    private ImageView img_Walking;

    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    private static final int SWIPE_MIN_DISTANCE = 5;
    private static final int SWIPE_THRESHOLD_VELOCITY = 300;
    private int activeFeature = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_sliding);
        img_Brain = (ImageView) findViewById(R.id.img_brain);
        img_Heart = (ImageView) findViewById(R.id.img_heart);
        img_Walking = (ImageView) findViewById(R.id.img_walking);
        pager = (ViewPager) findViewById(R.id.pager1);
//       customPager = (CustomViewPager) findViewById(R.id.pager1);



        // Assiging the Sliding Tab Layout View
        tabLayout = (TabLayout) findViewById(R.id.tab_layout1);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.heart_rate)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.heart_rate_history)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        img_Brain.setOnClickListener(this);
        img_Walking.setOnClickListener(this);
        img_Heart.setOnClickListener(this);


        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), Numboftabs);
        pager.setAdapter(viewPageAdapter);
       // tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

///'pager.setPageTransformer(true, new ZoomOutPageTransformer());
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                pager.setEnabled(false);;

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        pager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                view.getParent().requestDisallowInterceptTouchEvent(true);
//                return false; }
//        });

//        customPager.setPagingEnabled(false);



    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.img_walking:
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanim);
                img_Walking.startAnimation(animation);
                img_Walking.setBackgroundColor(Color.parseColor("#03a9f4"));
                img_Walking.setBackgroundResource(R.drawable.bg_circular);
                img_Heart.setBackgroundResource(R.drawable.img_border);
                img_Brain.setBackgroundResource(R.drawable.img_border);
                break;
            case R.id.img_brain:
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanim);
                img_Brain.startAnimation(animation1);
                img_Brain.setBackgroundColor(Color.parseColor("#03a9f4"));
                img_Brain.setBackgroundResource(R.drawable.bg_circular);
                img_Heart.setBackgroundResource(R.drawable.img_border);
                img_Walking.setBackgroundResource(R.drawable.img_border);

                break;
            case R.id.img_heart:
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanim);
                img_Heart.startAnimation(animation2);
                img_Heart.setBackgroundColor(Color.parseColor("#03a9f4"));
                img_Heart.setBackgroundResource(R.drawable.bg_circular);
                img_Brain.setBackgroundResource(R.drawable.img_border);
                img_Walking.setBackgroundResource(R.drawable.img_border);

                break;

        }


    }

//    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
//        private static final float MIN_SCALE = 0.85f;
//        private static final float MIN_ALPHA = 0.5f;
//
//        public void transformPage(View view, float position) {
//            int pageWidth = view.getWidth();
//            int pageHeight = view.getHeight();
//
//            if (position < -1) { // [-Infinity,-1)
//                // This page is way off-screen to the left.
//                view.setAlpha(0f);
//
//            } else if (position <= 1) { // [-1,1]
//                // Modify the default slide transition to shrink the page as well
//                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
//                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
//                if (position < 0) {
//                    view.setTranslationX(horzMargin - vertMargin / 2);
//                } else {
//                    view.setTranslationX(-horzMargin + vertMargin / 2);
//                }
//
//                // Scale the page down (between MIN_SCALE and 1)
//                view.setScaleX(scaleFactor);
//                view.setScaleY(scaleFactor);
//
//                // Fade the page relative to its size.
//                view.setAlpha(MIN_ALPHA +
//                        (scaleFactor - MIN_SCALE) /
//                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));
//
//            } else { // (1,+Infinity]
//                // This page is way off-screen to the right.
//                view.setAlpha(0f);
//            }
//        }
//    }


}
