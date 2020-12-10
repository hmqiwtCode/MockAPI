package com.quy.mockapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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
    private Button btnCreate;
    private Switch switchComplete;
    private EditText txtWork;
    private WorkAdapter workAdapter;
    private List<Work> listworks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvWork = findViewById(R.id.rvWork);
        btnCreate = findViewById(R.id.btnCreate);
        switchComplete = findViewById(R.id.switchComplete);
        txtWork = findViewById(R.id.txtWork);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://5fd220e5b485ea0016eef7ea.mockapi.io/todo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        workApi = retrofit.create(WorkApi.class);


        addWorks();
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
                listworks = response.body();
                workAdapter = new WorkAdapter(listworks,MainActivity.this);
                rvWork.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                rvWork.setAdapter(workAdapter);
            }

            @Override
            public void onFailure(Call<List<Work>> call, Throwable t) {
                Log.e("size",t.toString());
            }
        });
    }

    private void addWorks(){
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Work work = new Work(txtWork.getText().toString(),System.currentTimeMillis(),switchComplete.isChecked());
                resetForm();
                Call<Work> call = workApi.createWork(work);
                call.enqueue(new Callback<Work>() {
                    @Override
                    public void onResponse(Call<Work> call, Response<Work> response) {
                        if (!response.isSuccessful()){
                            Log.e("size","loi nao");
                            return;
                        }
                        Log.e("size",response.body().toString());
                        listworks.add(response.body());
                        workAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Work> call, Throwable t) {
                        Log.e("size",t.toString());
                    }
                });
            }
        });
    }

    private void resetForm(){
        txtWork.setText("");
        switchComplete.setChecked(false);
    }
}