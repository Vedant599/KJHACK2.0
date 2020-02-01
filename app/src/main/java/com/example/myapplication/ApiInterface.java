package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("get_startup_growth_info.php")
    Call<List<Growth>> getstartupgrowthinfo();

}
