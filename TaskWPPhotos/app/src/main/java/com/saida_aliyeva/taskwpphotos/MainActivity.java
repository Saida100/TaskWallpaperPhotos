package com.saida_aliyeva.taskwpphotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Urls urls;
    RecyclerView recyclerView;
    RVAdapter rvAdapter;
    List<Urls> urlsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        ApiInit apiInit = new ApiInit();
        apiInit.initRetrofit();
        APIInterface apiInterface = apiInit.getClient();
        apiInterface.getImages("ac8d70ff24d960769512452d0bfd73267661e916f39e5d204c852cafa75786a4")
                .enqueue(new Callback<List<Example>>() {
                    @Override
                    public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                    List<Example> exampleList = response.body();
                        Log.e("exampleList", response.body().toString());
                        urlsList = new ArrayList<>();
                        for (int i = 0; i < exampleList.size(); i++) {
                            Log.e("exampleList(i)", exampleList.get(i).toString());
                            urls = new Urls();
                            urls.setRaw(exampleList.get(i).getUrls().getRaw());
                            urls.setFull(exampleList.get(i).getUrls().getFull());
                            urls.setRegular(exampleList.get(i).getUrls().getRegular());
                            urls.setSmall(exampleList.get(i).getUrls().getSmall());
                            urls.setThumb(exampleList.get(i).getUrls().getThumb());
                                urlsList.add(urls);

                        }
                        rvAdapter = new RVAdapter(urlsList, getApplicationContext(), new Listener() {

                            @Override
                            public void onPhotoClick(Urls urls) {
                                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                                Bundle bundle=new Bundle();
                                bundle.putString("image",urls.getFull().toString());
                                intent.putExtras(bundle);
                                startActivity(intent);

                            }

                        });
                        StaggeredGridLayoutManager   staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(staggeredGridLayoutManager);
                        recyclerView.setAdapter(rvAdapter);


                    }


                    @Override
                    public void onFailure(Call<List<Example>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });




    }


}
