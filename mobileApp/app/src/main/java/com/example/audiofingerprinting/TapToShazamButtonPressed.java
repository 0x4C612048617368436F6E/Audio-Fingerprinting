package com.example.audiofingerprinting;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
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

import org.w3c.dom.Text;

public class TapToShazamButtonPressed extends AppCompatActivity {

    private boolean isRunning = true;
    private byte dotCounter = 0;
    private Handler handler = new Handler();
    @NonNull ImageView exit;
    @NonNull TextView listening;
    @NonNull TextView dotProgress;
    @NonNull Button pulseAnimation;
    @NonNull Button pulseAnimation2;
    final int SAMPLERATE = 44100;
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
        dotProgress = (TextView) findViewById(R.id.dotProgress);
        //larger Animation
        pulseAnimation2 = (Button) findViewById(R.id.pulseAnimation2);
        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("Debug","Exit pressed");
                finish();
            }
        });
        createAnimation(dotProgress,R.anim.fade_in_out);
        //create pulse animation
        createAnimation(pulseAnimation,R.anim.pulse_animation);
        createAnimation(pulseAnimation2,R.anim.pulse_animation2);
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
                dotProgress.setText("");
                dotProgress.setText(dotProgress.getText().toString().concat(numOfDots));
                handler.postDelayed(this,500);
            }
        },500);
    }

    //working with PCM
//    public void PCM(){
//        int bufferSize = AudioTrack.getMinBufferSize(SAMPLERATE, AudioFormat.CHANNEL_OUT_MONO,AudioFormat.ENCODING_PCM_16BIT);
//        //create AudioRecord object, also check if permission available
//        AudioRecord record = (AudioRecord) new AudioRecord(MediaRecorder.AudioSource.DEFAULT,SAMPLERATE,AudioFormat.CHANNEL_OUT_MONO,AudioFormat.ENCODING_PCM_16BIT,bufferSize);
//
//        //check if things didn't work as expected
//        if(bufferSize == AudioTrack.ERROR || bufferSize == AudioTrack.ERROR_BAD_VALUE){
//
//        }
//    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
