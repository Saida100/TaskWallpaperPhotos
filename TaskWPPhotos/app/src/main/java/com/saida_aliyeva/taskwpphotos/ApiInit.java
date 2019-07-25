package com.saida_aliyeva.taskwpphotos;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInit {

    Retrofit retrofit;
    APIInterface apiInterface;

    public void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpLogging())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public OkHttpClient httpLogging() {
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient=okHttpClient.newBuilder().addInterceptor(logging).build();
        return okHttpClient;

    }

    public APIInterface getClient() {
        if (apiInterface == null) {
            apiInterface =retrofit.create(APIInterface.class);
        }
        return apiInterface;
    }


}
