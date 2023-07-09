package com.devdroid.flying_fish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity5 extends AppCompatActivity {

    RecyclerView recyclerView;



    Button btneasy,btnmedium,btnhard;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        recyclerView=findViewById(R.id.recyclerview);

        listingdata();
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

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
    private void listingdata(){
        ApiInterface apiInterface=Retrofit.getRetrofit().create(ApiInterface.class);
        Call<List<Pojo>> listingdata=apiInterface.getposts();
        listingdata.enqueue(new Callback<List<Pojo>>() {
            @Override
            public void onResponse(Call<List<Pojo>> call, Response<List<Pojo>> response) {
                if (response.isSuccessful()){
                    recycleradapter adapter=new recycleradapter(response.body());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pojo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failure",Toast.LENGTH_SHORT);

            }
        });
    }

    class recycleradapter extends RecyclerView.Adapter<recycleradapter.MyViewHolder>{

        List<Pojo> list;

        public recycleradapter(List<Pojo> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public recycleradapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // inflate the layout here
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_recyclerview, parent ,false);
            recycleradapter.MyViewHolder viewHolder=new recycleradapter.MyViewHolder(view);
            //used parent with false so that all items are of same size
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull recycleradapter.MyViewHolder holder, int position) {
            holder.name.setText(list.get(position).getName());
            holder.detail.setText(list.get(position).getUsername());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView name,detail;
            ImageView imageView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                name=itemView.findViewById(R.id.Name);
                detail=itemView.findViewById(R.id.detail);
                imageView=itemView.findViewById(R.id.imageView);
            }
        }
    }
}