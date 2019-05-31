package com.online.java.com.online;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.a4th_assignmenr.R;
import com.online.java.API.UserAPI;
import com.online.java.model.LoginSignupResponse;
import com.online.java.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkUser() {
        UserAPI userAPI = Url.getInstance().create(UserAPI.class);

        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        Call<LoginSignupResponse> usercall = userAPI.checkUser(username, password);
        usercall.enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Invalid username/password", Toast.LENGTH_LONG).show();
                    return;
                } else {

                    if (response.body().getSuccess()) {
                        Url.Cookie = response.headers().get("Set-Cookie");
                        Intent intent = new Intent(LoginActivity.this, Dashboard_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
