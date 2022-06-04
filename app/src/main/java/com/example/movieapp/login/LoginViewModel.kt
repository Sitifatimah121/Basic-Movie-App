package com.example.movieapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.UserModel
import com.example.movieapp.model.UserPreferences
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: UserPreferences) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun login() {
        viewModelScope.launch {
            pref.login()
        }
    }
}