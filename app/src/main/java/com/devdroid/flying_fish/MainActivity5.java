package com.devdroid.flying_fish;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity5 extends AppCompatActivity {

    Button btneasy,btnmedium,btnhard;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btneasy=(Button) findViewById(R.id.btneasy);
        btneasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity5.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        btnmedium=(Button) findViewById(R.id.btnmedium);
        btnmedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity5.this,MainActivity6.class);
                startActivity(intent);
            }
        });

        btnhard=(Button) findViewById(R.id.btnhard);
        btnhard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity5.this,MainActivity7.class);
                startActivity(intent);
            }
        });
    }
}