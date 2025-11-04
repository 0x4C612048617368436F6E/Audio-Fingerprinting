package com.example.audiofingerprinting;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //create new fragment instance if non exists
        if(savedInstanceState == null){
            //create new fragment
            @NonNull NavigationFragment navigationFragment = new NavigationFragment();
            @NonNull FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.navigation_fragment,navigationFragment).commit();
        }
        @NonNull  Button tapToShazam = (Button) findViewById(R.id.buttonTapToShazam);
        @NonNull Button addToDatabase = (Button) findViewById(R.id.addToDatabase);
        @NonNull ImageView tapToShazamImageIcon = (ImageView) findViewById(R.id.buttonIcon);
        tapToShazam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //do some stuff
                Log.d("Debug","Switch to TapToShaza,ButtonPressed Activity");
                switchToTapToShazamButtonPressedActivity();
            }
        });


        addToDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //do some stuff
                Log.d("Test","Add to database");
            }
        });

        createAnimation(tapToShazam,R.anim.scale);
        createAnimation(tapToShazamImageIcon,R.anim.scale);

    }

    public void createAnimation(@NonNull View view, @NonNull int animResource){
        Animation animation = (Animation) AnimationUtils.loadAnimation(MainActivity.this,animResource);
        view.startAnimation(animation);
    }

    public void switchToTapToShazamButtonPressedActivity(){
        Intent switchActivity = (Intent) new Intent(MainActivity.this, TapToShazamButtonPressed.class);
        startActivity(switchActivity);
    }
}

