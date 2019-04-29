package com.dx777.yasirnramaya.aburafelsystem.login

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.Call

interface SignInInterface {
    @POST("login")
    @FormUrlEncoded
    fun registrationPost(@Field("email") email: String,
                         @Field("password") password: String): Call<LoginResponse>
}
