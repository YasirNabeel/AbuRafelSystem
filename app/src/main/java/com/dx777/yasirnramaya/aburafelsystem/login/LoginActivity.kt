package com.dx777.yasirnramaya.aburafelsystem.login


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dx777.yasirnramaya.aburafelsystem.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                Toast.makeText(this@LoginActivity, "Enter user info", Toast.LENGTH_LONG).show()
            }

        }

    }


    private fun logIn(email: String, pass: String) {

        var mAPIService: NetworkInInterface

        mAPIService = ApiUtils.apiService

        mAPIService.loginPost(email, pass).enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginProgressBar?.visibility = View.GONE

                if (response.isSuccessful()) {
                    cacheObj.tokenCache = response.body()!!.token
                    val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginProgressBar?.visibility = View.GONE
                Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
