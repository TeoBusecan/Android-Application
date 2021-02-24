package com.example.myapplication.api;


import com.example.myapplication.model.ChangePassword;
import com.example.myapplication.model.User;
import com.example.myapplication.model.UserInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @POST("user/login")
    Call<String> verifyuser(
            @Body User user
    );

    @GET("user/get/{username}")
    Call<UserInfo> getUserById(
            @Path("username") String username
    );

    @PUT("user/updateUser/{id}")
    Call<UserInfo> updateuser(
            @Path("id") int id,
            @Body UserInfo user
    );

    @PUT("user/changePassword/{id}")
    Call<Boolean> changePass(
            @Path("id") int id,
            @Body ChangePassword changePassword
    );
}
