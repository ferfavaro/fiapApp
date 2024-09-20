package com.fiap.fiapapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.textfield.TextInputEditText
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login)

        firebaseAuth = FirebaseAuth.getInstance()

        val edit_email = findViewById<TextInputEditText>(R.id.edit_email) // Change to email
        val edit_password = findViewById<TextInputEditText>(R.id.edit_password)
        val btn_login = findViewById<Button>(R.id.btn_login)

        btn_login.setOnClickListener {
            val email = edit_email.text.toString() // Use email instead of phone number
            val password = edit_password.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginUser(email, password) // Pass email to loginUser
        }
    }

    private fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val intent =
                    Intent(this, MapCarActivity::class.java) // Change to MapCarActivity.kt
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Login Failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}