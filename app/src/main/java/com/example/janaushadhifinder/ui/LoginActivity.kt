package com.example.janaushadhifinder.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.janaushadhifinder.MainActivity
import com.example.janaushadhifinder.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.etLoginEmail)
        val password = findViewById<EditText>(R.id.etLoginPassword)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val signupText = findViewById<TextView>(R.id.tvGoSignup)
        val sharedPref: SharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        loginBtn.setOnClickListener {
            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString().trim()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check against saved credentials in SharedPreferences
            val savedEmail = sharedPref.getString("email", null)
            val savedPassword = sharedPref.getString("password", null)
            
            // Also allow demo credentials
            if ((userEmail == savedEmail && userPassword == savedPassword) || 
                (userEmail == "admin@gmail.com" && userPassword == "123456")) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

        signupText.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}