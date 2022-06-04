package com.example.movieapp.main

import androidx.lifecycle.*
import com.example.movieapp.MovieResponse
import com.example.movieapp.model.UserModel
import com.example.movieapp.model.UserPreferences
import com.example.movieapp.remote.ApiConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreferences) : ViewModel(){
    private var _movie = MutableLiveData<MovieResponse>()
    val movie get() = _movie

    fun getAllMovie() {
        CoroutineScope(Dispatchers.IO).launch {
            ApiConfig.getApiService().getMovie().let {
                _movie.postValue(it.body())
            }
        }
    }

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}