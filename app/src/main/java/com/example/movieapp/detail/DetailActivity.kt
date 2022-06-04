package com.example.movieapp.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityDetailBinding
import com.example.movieapp.remote.Movie

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val movie = intent.getSerializableExtra("MOVIE_KEY") as Movie
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w342${movie.getPosterPath()}")
                .centerCrop()
                .into(ivMovie);
            tvTitle.text = movie.getTitle()
            tvOverview.text = movie.getOverview()
        }
    }
}