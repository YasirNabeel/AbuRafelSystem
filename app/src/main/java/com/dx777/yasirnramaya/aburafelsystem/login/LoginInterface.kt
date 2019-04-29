package com.dx777.yasirnramaya.aburafelsystem.login

import retrofit2.http.Field
import retrofit2.http.POST

interface SignInInterface {
    @POST("login")
    fun loginMethod(@Field("email") email: String,
                     @Field("password") password: String): retrofit2.Call<LoginResponse>
}