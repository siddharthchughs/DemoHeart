package com.bpk.mydemopoc;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VibratonActivity extends AppCompatActivity implements View.OnClickListener {


    private Vibrator vibrator;
    private Button btClock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibraton);
        btClock  = findViewById(R.id.btn_Vibrate);
        vibrator=vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

       btClock.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.btn_Vibrate:


                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(200);
                }


                break;
        }



    }
}
