package com.example.audiofingerprinting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddToDatabase extends AppCompatActivity {

    private String backendURL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_to_database);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        @NonNull ImageView exit = (ImageView) findViewById(R.id.exit);

        @NonNull Button addButton = (Button) findViewById(R.id.addToDatabase);

        exit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //do some stuff
                Log.d("Test","Add to database");
                finish();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("Test","Add to database");
                //get URL
                EditText youtubeURLEditText = (EditText) findViewById(R.id.youtubeURL);
                if(!(youtubeURLEditText.getText()).toString().trim().isEmpty()) {
                    sendUrl.asynchronousPostRequest(String.format("{\"link\":\"%s\"}", youtubeURLEditText.getText()));
                }
            }
        });

    }
}