package com.example.firebase_login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase_login.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {


    private val auth = FirebaseAuth.getInstance()
    private val repository = AuthRepository()

    private val _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean> = _result

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun login(email: String, password: String) {
        repository.login(email, password) { success, message ->
            if (success) {
                _result.value = true
            } else {
                _error.value = message?: "Login Gagal"
            }
        }
    }

    fun register(email: String, password: String) {
        repository.register(email, password) { success, message ->
            if (success) {
                _result.value = true
            } else {
                _error.value = message?: "Register Gagal"
            }
        }
    }
    fun logout(){
        repository.logout()
    }

    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }
}