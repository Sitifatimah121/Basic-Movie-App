package com.example.movieapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.databinding.ActivitySplashBinding
import com.example.movieapp.login.LoginActivity
import com.example.movieapp.main.MainActivity
import com.example.movieapp.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

     lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences.getInstance(dataStore)

        val splashTime: Long = 1500
        Handler().postDelayed({
            Handler(Looper.getMainLooper())
            startActivity(Intent(this, LoginActivity::class.java))
            lifecycleScope.launch {
                userPreferences.getUser().collect {
                    val result = if (it.isLogin == true)
                        MainActivity::class.java
                    else
                        LoginActivity::class.java
                    startActivity(Intent(this@SplashActivity, result))
                    finish()
                }
            }

        }, splashTime)
    }
}




