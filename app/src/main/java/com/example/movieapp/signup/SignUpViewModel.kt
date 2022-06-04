package com.example.movieapp.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.UserModel
import com.example.movieapp.model.UserPreferences
import kotlinx.coroutines.launch

class SignUpViewModel (private val pref: UserPreferences) : ViewModel() {
    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }
}