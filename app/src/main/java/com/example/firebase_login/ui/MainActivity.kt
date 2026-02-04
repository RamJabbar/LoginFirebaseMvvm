package com.example.firebase_login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_login.R
import com.example.firebase_login.viewmodel.AuthViewModel

class MainActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                toast("Email & password wajib diisi")
            } else {
                authViewModel.login(email, password)
            }
        }

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                toast("Email & password wajib diisi")
            } else {
                authViewModel.register(email, password)
            }
        }

        authViewModel.result.observe(this) {
            startActivity(Intent(this, HomePageActivity::class.java))
            finish()
        }

        authViewModel.error.observe(this) { message ->
          message?.let {
            toast(it)
          }
        }
    }

    override fun onStart() {
        super.onStart()
        if (authViewModel.isLoggedIn()) {
            goToHome()
        }
    }

    private fun goToHome() {
        startActivity(Intent(this, HomePageActivity::class.java))
        finish()
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}