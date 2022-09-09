package com.example.network.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.network.R;
import com.example.network.model.Item;
import com.example.network.network.ApiManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tvDate, tvDes, tvTitle;
    ImageView ivCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        callApiGetData();
    }

    private void initView() {
        tvDate = findViewById(R.id.tvDate);
        tvDes = findViewById(R.id.tvDes);
        tvTitle = findViewById(R.id.tvTitle);
        ivCover = findViewById(R.id.ivCover);
    }

    private void callApiGetData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.apiGetData().enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Log.d("TAG", "onResponse: "+response);
                Item model = response.body();
                tvDate.setText(model.getDate());
                tvTitle.setText(model.getTitle());
                tvDes.setText(model.getContent().getDescription());
                Glide.with(MainActivity.this).load(model.getImage()).into(ivCover);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t);
            }
        });
    }
}