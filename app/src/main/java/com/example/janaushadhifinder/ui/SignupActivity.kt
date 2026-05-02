package com.example.janaushadhifinder.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.janaushadhifinder.R

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val email = findViewById<EditText>(R.id.etSignupEmail)
        val password = findViewById<EditText>(R.id.etSignupPassword)
        val signupBtn = findViewById<Button>(R.id.btnSignup)
        val loginText = findViewById<TextView>(R.id.tvGoLogin)

        val sharedPref: SharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        signupBtn.setOnClickListener {
            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString().trim()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save locally
            val editor = sharedPref.edit()
            editor.putString("email", userEmail)
            editor.putString("password", userPassword)
            editor.apply()

            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        loginText.setOnClickListener {
            finish()
        }
    }
}