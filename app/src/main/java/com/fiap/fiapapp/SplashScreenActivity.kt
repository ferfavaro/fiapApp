package com.fiap.fiapapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.fiap.fiapapp.Model.DriverInfoModel
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var driverInfoRef: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_register)

        // Initialize Firebase
        init()

        // Show the registration layout when the activity starts
        showRegisterLayout()
    }

    private fun init() {
        val database = FirebaseDatabase.getInstance()
        driverInfoRef = database.getReference(Common.DRIVER_INFO_REFERENCE)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun showRegisterLayout() {
        val builder = AlertDialog.Builder(this, R.style.DialogTheme)
        val itemView = LayoutInflater.from(this).inflate(R.layout.layout_register, null)

        val edit_first_name = itemView.findViewById<TextInputEditText>(R.id.edit_first_name)
        val edit_last_name = itemView.findViewById<TextInputEditText>(R.id.edit_last_name)
        val edit_email =
            itemView.findViewById<TextInputEditText>(R.id.edit_email) // New email field
        val edit_phone_number = itemView.findViewById<TextInputEditText>(R.id.edit_phone_number)
        val edit_password = itemView.findViewById<TextInputEditText>(R.id.edit_password)
        val btn_register = itemView.findViewById<Button>(R.id.btn_register)

        builder.setView(itemView)
        val dialog = builder.create()
        dialog.show()

        btn_register.setOnClickListener {
            registerUser(
                edit_first_name.text.toString(),
                edit_last_name.text.toString(),
                edit_email.text.toString(), // Include email in registration
                edit_phone_number.text.toString(),
                edit_password.text.toString(),
                dialog
            )
        }
    }

    private fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        password: String,
        dialog: AlertDialog
    ) {
        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Register the user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val currentUser = firebaseAuth.currentUser
                if (currentUser != null) {
                    val model = DriverInfoModel().apply {
                        this.firstName = firstName
                        this.lastName = lastName
                        this.email = email
                        this.phoneNumber = phoneNumber
                        this.rating = 0.0
                    }
                    driverInfoRef.child(currentUser.uid)
                        .setValue(model)
                        .addOnSuccessListener {
                            Toast.makeText(this@SplashScreenActivity, "Registered Successfully", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                            Common.currentUser = model

                            // Navigate to LoginActivity
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish() // Optional: Finish this activity if you don't want to return to it
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this@SplashScreenActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            Log.e("SplashScreenActivity", "Failed to save user data: ${e.message}")
                        }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Registration Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("SplashScreenActivity", "Registration error: ${e.message}")
            }
    }

}
