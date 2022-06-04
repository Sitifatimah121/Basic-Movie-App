package com.example.movieapp.main

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.ListMovieAdapter
import com.example.movieapp.remote.Movie
import com.example.movieapp.SplashActivity
import com.example.movieapp.ViewModelFactory
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.model.UserPreferences

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        binding.icLogout.setOnClickListener {
            mainViewModel.logout()
        }
    }

    private fun showRecylerList(listMovie: List<Movie>) {
        val listMovieAdapter = ListMovieAdapter(listMovie, this)

        binding.rvMovie.apply {
            setHasFixedSize(true)
            layoutManager =
                if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                    GridLayoutManager(this@MainActivity, 2)
                else LinearLayoutManager(this@MainActivity)
            adapter = listMovieAdapter
        }
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[MainViewModel::class.java]

        mainViewModel.getUser().observe(this) { user ->
            if (user.isLogin) {
                mainViewModel.getAllMovie()

                mainViewModel.movie.observe(this) {
                    showRecylerList(it.results)
                }
            } else {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
        }
    }

    companion object{
        const val EXTRA_MOVIE = "extra"
    }
}