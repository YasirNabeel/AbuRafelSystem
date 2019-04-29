package com.dx777.yasirnramaya.aburafelsystem.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.chibatching.kotpref.Kotpref
import com.dx777.yasirnramaya.aburafelsystem.R
import com.dx777.yasirnramaya.aburafelsystem.servies.cacheObj
import com.dx777.yasirnramaya.aburafelsystem.activities.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Kotpref.init(this)

        Handler().postDelayed({

            if (cacheObj.tokenCache.isEmpty()) {
                val signInIntent = Intent(this, LoginActivity::class.java)
                startActivity(signInIntent)
                finish()
            } else {
                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }
        }, 1000)
    }
}
