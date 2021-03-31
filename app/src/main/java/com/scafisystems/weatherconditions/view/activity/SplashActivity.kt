package com.scafisystems.weatherconditions.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scafisystems.weatherconditions.R
import kotlinx.coroutines.*

/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */

class SplashActivity : AppCompatActivity() {

    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityScope.launch {
            delay(3000)

            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}