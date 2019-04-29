package com.dx777.yasirnramaya.aburafelsystem

import com.chibatching.kotpref.KotprefModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object cacheObj : KotprefModel() {
    var tokenCache by stringPref(default = "")
}


object RetrofitClient {

    var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            //TODO While release in Google Play Change the Level to NONE
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit

    }
}

object ApiUtils {

    val BASE_URL = "https://aborafel.herokuapp.com/api/v1/auth/"

    val apiService: NetworkInInterface
        get() = RetrofitClient.getClient(BASE_URL)!!.create(NetworkInInterface::class.java)

}