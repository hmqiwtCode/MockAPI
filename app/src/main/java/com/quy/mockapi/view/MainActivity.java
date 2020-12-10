package com.quy.mockapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.quy.mockapi.R;
import com.quy.mockapi.adapter.WorkAdapter;
import com.quy.mockapi.api.WorkApi;
import com.quy.mockapi.entity.Work;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private WorkApi workApi;
    private RecyclerView rvWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvWork = findViewById(R.id.rvWork);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://5fd220e5b485ea0016eef7ea.mockapi.io/todo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        workApi = retrofit.create(WorkApi.class);

        getWorks();
    }




    private void getWorks(){
        Call<List<Work>> call = workApi.getWork();
        call.enqueue(new Callback<List<Work>>() {
            @Override
            public void onResponse(Call<List<Work>> call, Response<List<Work>> response) {
                if(!response.isSuccessful()){
                    return;
                }

                WorkAdapter workAdapter = new WorkAdapter(response.body(),MainActivity.this);
                rvWork.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                rvWork.setAdapter(workAdapter);
            }

            @Override
            public void onFailure(Call<List<Work>> call, Throwable t) {
                Log.e("size",t.toString());
            }
        });
    }
}