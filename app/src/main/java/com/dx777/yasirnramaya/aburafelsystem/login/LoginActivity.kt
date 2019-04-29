package com.dx777.yasirnramaya.aburafelsystem.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.dx777.yasirnramaya.aburafelsystem.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var email: String
        var pass: String

        loginButton.setOnClickListener {
            loginProgressBar?.visibility = View.VISIBLE

            if (emailEditText.text.toString().isNotEmpty() && passEditText.text.toString().isNotEmpty()) {
                email = emailEditText.text.toString()
                pass = passEditText.text.toString()
                logIn(email, pass)
            } else {
                Toast.makeText(this@LoginActivity,"Enter user info", Toast.LENGTH_LONG).show()
            }

        }

    }


    private fun logIn(email: String, pass: String) {


        //Variable declaration
        var mAPIService: SignInInterface? = null

        //After oncreate

        mAPIService = ApiUtils.apiService

        //Some Button click

        mAPIService!!.registrationPost(email, pass).enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginProgressBar?.visibility = View.GONE
                this@LoginActivity.runOnUiThread {
                    Toast.makeText(this@LoginActivity, response.body().toString(), Toast.LENGTH_SHORT).show()
                }

                if (response.isSuccessful()) {
                    Toast.makeText(this@LoginActivity, response.body()?.token, Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginProgressBar?.visibility = View.GONE
                this@LoginActivity.runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Erorr", Toast.LENGTH_SHORT).show()
                }
            }
        })

//        val retrofit = Retrofit.Builder()
//            .baseUrl(Consts.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val signInInterface = retrofit.create(SignInInterface::class.java)
//
//        signInInterface.loginMethod(email, pass)
//            .enqueue(object : Callback<LoginResponse> {
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_SHORT).show()
//                    loginProgressBar?.visibility = View.GONE
//                }
//
//                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                    Toast.makeText(this@LoginActivity, response.body()?.token, Toast.LENGTH_SHORT).show()
//
//                    loginProgressBar?.visibility = View.GONE
//                    response.body()?.let {
//                        cacheObj.tokenCache = it.token
//                        val startMainIntent = Intent(this@LoginActivity, MainActivity::class.java)
//                        startActivity(startMainIntent)
//                        finish()
//                    }
//                }
//            })
    }
}
