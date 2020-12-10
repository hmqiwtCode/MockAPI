package com.quy.mockapi.api;

import com.quy.mockapi.entity.Work;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WorkApi {
    @GET("work")
    Call<List<Work>> getWork();

    @GET("work/{id}")
    Call<Work> getWorkById(@Path("id") String id);

    @POST("work")
    Call<Work> createWork(@Body Work work);



}
