package com.example.firebase_login.ui

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase_login.data.repository.AuthRepository

class LoginViewModel : ViewModel() {

    private val repo = AuthRepository()

    val loginSuccess = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun login(email: String, password: String) {
        if (email.isEmpty()||password.isEmpty()){
            errorMessage.value =  "Email dan Password Wajib Di isi"
            return
    }
        repo.login(email, password){success, error ->
            if(success){
                loginSuccess.value = true
            }else{
                errorMessage.value = error?: "Login Gagal"
            }
        }
    }
}