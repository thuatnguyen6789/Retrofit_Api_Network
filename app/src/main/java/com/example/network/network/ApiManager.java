package com.example.network.network;

import com.example.network.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    String SERVER = "https://api-demo-anhth.herokuapp.com";

    @GET("/data.json")
    Call<Item> apiGetData();

    @GET("/datas.json")
    Call<List<Item>> apiGetListData();

}
