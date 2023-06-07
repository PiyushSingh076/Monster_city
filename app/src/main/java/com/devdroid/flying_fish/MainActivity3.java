package com.devdroid.flying_fish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;

public class MainActivity3 extends AppCompatActivity {

    Button btnhm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnhm=(Button) findViewById(R.id.btnhm);
        btnhm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity3.this,MainActivity5.class);
                startActivity(intent);
            }
        });
    }
}