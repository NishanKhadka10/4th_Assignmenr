package com.online.java.API;

import com.online.java.model.ImageResponse;
import com.online.java.model.Items;
import com.online.java.model.LoginSignupResponse;
import com.online.java.model.Users;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserAPI {
    @POST("users/signup")
    Call<Void> addHero(@Body Users users);

    @FormUrlEncoded
    @POST("items")
    Call<Void> addItems(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("users/login")
    Call<LoginSignupResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("items")
    Call<List<Items>> getAllItems();
}