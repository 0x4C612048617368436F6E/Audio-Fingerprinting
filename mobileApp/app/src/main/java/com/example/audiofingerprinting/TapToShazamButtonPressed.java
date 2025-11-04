package com.example.audiofingerprinting;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TapToShazamButtonPressed extends AppCompatActivity {

    private boolean isRunning = true;
    private byte dotCounter = 0;
    private Handler handler = new Handler();
    @NonNull ImageView exit;
    @NonNull TextView listening;
    @NonNull Button pulseAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tap_to_shazam_button_pressed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        exit = (ImageView) findViewById(R.id.exit);
        listening = (TextView) findViewById(R.id.listening) ;
        pulseAnimation = (Button) findViewById(R.id.pulseAnimation);
        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("Debug","Exit pressed");
                finish();
            }
        });
        createAnimation(listening,R.anim.fade_in_out);
        //create pulse animation
        createAnimation(pulseAnimation,R.anim.pulse_animation);
        isListeningAnimation();
        //can start listening here

    }

    public void createAnimation(@NonNull View view, @NonNull int animResource){
        Animation animation = (Animation) AnimationUtils.loadAnimation(TapToShazamButtonPressed.this,animResource);
        view.startAnimation(animation);
    }

    public void isListeningAnimation(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isRunning) return;

                //handle dotCounter increment
                dotCounter = (byte) ((byte)((byte)dotCounter+(byte)1)%(byte)4);
                String numOfDots = new String(new char[dotCounter]).replace('\0','.');
                listening.setText("Loading".concat(numOfDots));
                handler.postDelayed(this,500);
            }
        },500);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
