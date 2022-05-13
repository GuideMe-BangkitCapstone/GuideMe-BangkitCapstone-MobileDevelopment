package com.capstone.guideme.utils

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.capstone.guideme.MainActivity
import com.capstone.guideme.R
import com.capstone.guideme.ui.signin.SigninActivity
import com.capstone.guideme.ui.signup.SignupActivity
import com.capstone.guideme.ui.welcome.WelcomeActivity

@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler(Looper.getMainLooper()).postDelayed({
            val moveToMain = Intent(this, MainActivity::class.java)
            startActivity(moveToMain)
            finish()
        }, 2000)
    }
}