package com.example.tz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import utils.MyObject

class SplashActivity : AppCompatActivity() {
    lateinit var handler:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        MyObject.slpash = true


        handler = Handler()

        handler.postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        },
            2000)
    }
}