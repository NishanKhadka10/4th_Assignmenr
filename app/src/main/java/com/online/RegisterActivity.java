package com.online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.a4th_assignmenr.R;

import java.util.HashMap;
import java.util.Map;

import API.UserAPI;
import model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class RegisterActivity extends AppCompatActivity {
    private EditText etFname,etLname, etUsername, etPassword;
    private Button btnRegister,btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin= findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void register(){
        String fname= etFname.getText().toString();
        String lname = etLname.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        Users users = new Users(fname,lname,username,password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call<Void> usersCall = userAPI.addHero(users);
        usersCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this,"Code" +response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this,"Successfully Saved",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Error" +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
