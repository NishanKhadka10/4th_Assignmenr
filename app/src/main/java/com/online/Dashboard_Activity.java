package com.online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.e.a4th_assignmenr.R;

import java.util.List;

import API.UserAPI;
import adapter.ItemsAdapter;
import model.Items;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class Dashboard_Activity extends AppCompatActivity {
    private Button btnAddItem;

    private RecyclerView recyclerView;
    private ImageView refresh;
    private List<Items> heroesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_);
        recyclerView = findViewById(R.id.recyclerview);
        btnAddItem = findViewById(R.id.btnAddItem);
        refresh = findViewById(R.id.refresh);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Activity.this,AddItemActivity.class);
                startActivity(intent);
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Activity.this,Dashboard_Activity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        UserAPI heroesAPI = retrofit.create(UserAPI.class);
        Call<List<Items>> listCall = heroesAPI.getAllItems();

        listCall.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {

                if (response.isSuccessful()){
                    heroesList = response.body();
                    ItemsAdapter adapter = new ItemsAdapter(Dashboard_Activity.this,heroesList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Dashboard_Activity.this));
                }
            }
            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Toast.makeText(Dashboard_Activity.this,"error",Toast.LENGTH_LONG).show();
            }
        });
    }
}
