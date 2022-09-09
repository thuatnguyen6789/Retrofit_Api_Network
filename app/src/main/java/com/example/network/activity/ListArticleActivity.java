package com.example.network.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.network.R;
import com.example.network.adapter.ArticleAdapter;
import com.example.network.model.Item;
import com.example.network.network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListArticleActivity extends AppCompatActivity {

    List<Item> listData = new ArrayList<>();
    ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_article);

        //B1: Data
        callApi();

        //B2: Layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //B3: Adapter
        adapter = new ArticleAdapter(this, listData);

        //B4
        RecyclerView rvArticle = findViewById(R.id.rvArticle);
        rvArticle.setLayoutManager(layoutManager);
        rvArticle.setAdapter(adapter);
    }

    private void callApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.apiGetListData().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                listData = response.body();
                adapter.reloadData(listData);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }
}