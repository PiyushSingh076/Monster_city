package com.devdroid.flying_fish;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity4 extends AppCompatActivity {

    Button btnhm2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnhm2=(Button) findViewById(R.id.btnhm2);
        btnhm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity4.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}