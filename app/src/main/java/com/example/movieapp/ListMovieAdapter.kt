package com.example.movieapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ItemRowMovieBinding
import com.example.movieapp.detail.DetailActivity
import com.example.movieapp.remote.Movie

class ListMovieAdapter(private val listMovie: List<Movie>, private val context: Context) : RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemRowMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieAdapter.ViewHolder {
        binding = ItemRowMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ListMovieAdapter.ViewHolder, position: Int) {
        val movie = listMovie[position]
        with(binding){
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w342${movie.getPosterPath()}")
                .into(imgItemAvatar)
            tvItemName.text = movie.getTitle()
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("MOVIE_KEY", movie)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listMovie.size

}