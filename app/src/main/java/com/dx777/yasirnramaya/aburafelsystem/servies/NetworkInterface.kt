package com.dx777.yasirnramaya.aburafelsystem.servies

import com.dx777.yasirnramaya.aburafelsystem.activities.login.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.Call

interface NetworkInInterface {
    @POST("login")
    @FormUrlEncoded
    fun loginPost(@Field("email") email: String,
                         @Field("password") password: String): Call<LoginResponse>
}
