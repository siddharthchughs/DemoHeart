package com.bpk.mydemopoc;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainHeartActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imgBtnav;
    private ImageView imgnav;
    private Button tv_heart_rate;
    private Button tv_heart_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_heart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        imgBtnav = findViewById(R.id.imgBtnav);
        imgnav = findViewById(R.id.imgnav);
        imgBtnav.setOnClickListener(this);
        imgnav.setOnClickListener(this);

        tv_heart_rate = (Button) findViewById(R.id.textView2);
        tv_heart_history = (Button) findViewById(R.id.textView);
        tv_heart_rate.setOnClickListener(this);
        tv_heart_history.setOnClickListener(this);
        loadFragment(new Demo1Fragment());
    }


    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.imgBtnav:
                differentOption();
                break;
            case R.id.imgnav:
                navigationOption();

                break;
            case R.id.textView2:

                btnStateChangedheartrate();

                break;
            case R.id.textView:
                btnStateChangedheartratehistory();
                break;


        }


    }

    public void differentOption() {
        Intent inUp = new Intent(getApplicationContext(), HeartActivity.class);
        getWindow().setEnterTransition(new Explode());
        startActivity(inUp);
    }

    public void navigationOption() {
        Intent inUp = new Intent(getApplication(), MainActivity.class);
/*        Bundle bundleTransition =
                ActivityOptions.makeCustomAnimation(getApplicationContext(),
                        R.anim.slide_in_left,R.anim.slide_out_left).toBundle();
*/
        startActivity(inUp);

    }


    void btnStateChangedheartrate() {

        tv_heart_history.setBackgroundResource(R.drawable.bg_btstatewhite);
        tv_heart_history.setTextColor(Color.parseColor("#F44336"));
        tv_heart_rate.setBackgroundResource(R.drawable.bg_btstate);
        tv_heart_rate.setTextColor(Color.parseColor("#ffffff"));
        loadFragment(new Demo1Fragment());
    }

    void btnStateChangedheartratehistory() {
        tv_heart_rate.setBackgroundResource(R.drawable.bg_btstatewhite);
        tv_heart_rate.setTextColor(Color.parseColor("#F44336"));
        tv_heart_history.setBackgroundResource(R.drawable.bg_btstate);
        tv_heart_history.setTextColor(Color.parseColor("#ffffff"));
        loadFragment(new DemoFourFragment());
    }


    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.container, fragment);
//        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit(); // save the changes
    }


}
