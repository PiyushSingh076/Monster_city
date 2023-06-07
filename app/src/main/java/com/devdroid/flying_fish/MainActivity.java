package com.devdroid.flying_fish;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.devdroid.flying_fish.MainActivity2;
import com.devdroid.flying_fish.R;

public class MainActivity extends AppCompatActivity {

    Button btnstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.song);
        mediaPlayer.start();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnstart=findViewById(R.id.btnstart);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MainActivity5.class);
                startActivity(intent);
            }
        });
    }
}