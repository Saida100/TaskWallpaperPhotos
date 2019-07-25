package com.saida_aliyeva.taskwpphotos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("photos/")
    Call<List<Example>> getImages(@Query("client_id") String client_id);


   // @GET("api/")
 //   Call<Example2> getImages2(@Query("key") String key);
}
