package br.com.lucaspires.tibiainfotracker.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import br.com.lucaspires.tibiainfotracker.AppConstants.TIME_SPLASH_TO_MAIN_ACTIVITY
import br.com.lucaspires.tibiainfotracker.R

internal class SplashScreen : AppCompatActivity(R.layout.activity_splash_screen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TIME_SPLASH_TO_MAIN_ACTIVITY)
    }
}