package com.bpk.mydemopoc.TabAdapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    private boolean enableSwipe;

    public CustomViewPager(Context context) {
        super(context);
        init();
    }

    public CustomViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
        this.enableSwipe = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(this.enableSwipe){
            return super.onTouchEvent(event);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        if(this.enableSwipe){
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    public void setPagingEnabled(boolean enabled){
        this.enableSwipe = enabled;
    }

    private void init() {
        enableSwipe = true;
    }

}