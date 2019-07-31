package com.saida_aliyeva.taskwpphotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main3ActivityPagination extends AppCompatActivity implements InfiniteScrollListener.OnLoadMoreListener {
    RecyclerView recyclerView;
    List<Urls> urlsList = new ArrayList<>();
    InfiniteScrollListener infiniteScrollListener;
    CustomAdapter adapter;
    Urls urls;
    List<Example> exampleList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_pagination);
        recyclerView = findViewById(R.id.recyclerView);

        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        infiniteScrollListener = new InfiniteScrollListener(staggeredGridLayoutManager, this);

        ApiInit apiInit = new ApiInit();
        apiInit.initRetrofit();
        APIInterface apiInterface = apiInit.getClient();
        apiInterface.getImages("ac8d70ff24d960769512452d0bfd73267661e916f39e5d204c852cafa75786a4")
                .enqueue(new Callback<List<Example>>() {
                    @Override
                    public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                        exampleList = response.body();
                        Log.e("exampleList2", response.body().toString());
                        urlsList = new ArrayList<>();
                        for (int i = 0; i < exampleList.size(); i++) {
                            Log.e("exampleList2(i)", exampleList.get(i).toString());
                            urls = new Urls();
                            urls.setRaw(exampleList.get(i).getUrls().getRaw());
                            urls.setFull(exampleList.get(i).getUrls().getFull());
                            urls.setRegular(exampleList.get(i).getUrls().getRegular());
                            urls.setSmall(exampleList.get(i).getUrls().getSmall());
                            urls.setThumb(exampleList.get(i).getUrls().getThumb());
                            urlsList.add(urls);

                        }
                        recyclerView.setLayoutManager(staggeredGridLayoutManager);
                        infiniteScrollListener.setLoaded();
                        recyclerView.addOnScrollListener(infiniteScrollListener);
                        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                        adapter = new CustomAdapter(getApplicationContext(), exampleList, new Listener() {


                            @Override
                            public void onPhotoClick(Example example) {
                                Intent intent = new Intent(Main3ActivityPagination.this, Main2Activity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("image", example.getUrls().getThumb());
                                bundle.putString("created_at", example.getCreatedAt());
                                bundle.putString("updated_at", example.getUpdatedAt());
                                bundle.putString("color", example.getColor());
                                bundle.putString("width", String.valueOf(example.getWidth()));
                                bundle.putString("height", String.valueOf(example.getHeight()));
                                bundle.putString("alt_description", example.getAlt_description());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Example>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


    }

    @Override
    public void onLoadMore() {
        adapter.addNullData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.removeNull();
                Log.e("exampleListOnLoad", "called");
                List<Example> exampleList2 = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Log.e("exampleList.get(i)", String.valueOf(exampleList.get(i)));
                    exampleList2.add(exampleList.get(i));
                }
                adapter.addData(exampleList);
                infiniteScrollListener.setLoaded();

            }
        }, 2000);
    }
}
