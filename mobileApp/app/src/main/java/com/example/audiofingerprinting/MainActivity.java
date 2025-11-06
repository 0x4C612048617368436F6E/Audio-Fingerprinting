package com.example.audiofingerprinting;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String[] permissions = {Manifest.permission.RECORD_AUDIO};
    List<String> PermissionsToRequest = new ArrayList<>();
    private static final int PERMISSION_REQUEST_CODE = 123;
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
                requestPermissions();

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
        //will pass in data
        startActivity(switchActivity);
    }

    public void requestPermissions() {
        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) {
                PermissionsToRequest.add(p);
            }
        }

        if (!PermissionsToRequest.isEmpty()) {
            //if not empty
            ActivityCompat.requestPermissions(this, PermissionsToRequest.toArray(new String[0]), PERMISSION_REQUEST_CODE);
        } else {
            Toast.makeText(this, "permissions already granted", Toast.LENGTH_SHORT).show();
            //If permission already granted, can switch
            switchToTapToShazamButtonPressedActivity();
        }
    }

    // Callback function that handles the
    // result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            List<String> deniedPermissions = new ArrayList<>();

            // Check which permissions were denied
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permissions[i]);
                }
            }

            if (deniedPermissions.isEmpty()) {

                // All permissions granted
                Toast.makeText(this, "All permissions granted", Toast.LENGTH_SHORT).show();
                switchToTapToShazamButtonPressedActivity();
            } else {

                // Some permissions were denied, show them in a Toast
                Toast.makeText(this, "Permissions denied: " + deniedPermissions, Toast.LENGTH_LONG).show();
                requestPermissions();
            }
        }
    }
}

