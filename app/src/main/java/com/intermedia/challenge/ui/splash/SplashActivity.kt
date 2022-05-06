package com.intermedia.challenge.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.intermedia.challenge.R
import com.intermedia.challenge.databinding.ActivitySplashBinding
import com.intermedia.challenge.ui.login.LoginActivity
import java.lang.String
import kotlin.Long

class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.raw.splash_animation).into(binding.ivSplashAnimation)

        object : CountDownTimer(1500, 500) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                goToLogin()
            }
        }.start()
    }

    private fun goToLogin() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        finish()
        startActivity(intent)
    }
}