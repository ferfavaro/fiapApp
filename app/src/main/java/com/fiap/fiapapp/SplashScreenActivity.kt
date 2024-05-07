package com.fiap.fiapapp

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.GoogleBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import android.view.View
import android.widget.Button
import com.fiap.fiapapp.Model.DriverInfoModel
import com.google.android.material.textfield.TextInputEditText

class SplashScreenActivity: AppCompatActivity() {


    companion object {
        private val LOGIN_REQUEST_CODE = 717
    }

    private lateinit var providers: List<AuthUI.IdpConfig>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener


    private lateinit var database:FirebaseDatabase
    private lateinit var driverInfoRef:DatabaseReference

    override fun onStart() {
        super.onStart()
        delaySplashScreen()
    }

    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // Autenticação bem-sucedida
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                Toast.makeText(this@SplashScreenActivity, "welcome: " + user.uid, Toast.LENGTH_SHORT).show()
            }
        } else {
            // Autenticação falhou ou cancelada
            Toast.makeText(this@SplashScreenActivity, "Authentication failed.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        if(firebaseAuth != null && listener != null) firebaseAuth.removeAuthStateListener(listener)
        super.onStop()
    }

    private fun delaySplashScreen() {
//        Completable.timer(3, TimeUnit.SECONDS,AndroidSchedulers.mainThread())
//            .subscribe {
//                firebaseAuth.addAuthStateListener(listener);
//            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)

        init()
    }

    private fun init() {
        database = FirebaseDatabase.getInstance()
        driverInfoRef = database.getReference(Common.DRIVER_INFO_REFERENCE)

        providers = listOf(
            AuthUI.IdpConfig.PhoneBuilder().build(),
            GoogleBuilder().build()
        )

        firebaseAuth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener { myFirebaseAuth ->
            val user = myFirebaseAuth.currentUser
            if(user != null)
            {
                checkUserFromFirebase()
            }


            else
                showLoginLayout()
        }
    }

    private fun checkUserFromFirebase() {
        driverInfoRef
            .child(FirebaseAuth.getInstance().currentUser!!.uid )
            .addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        Toast.makeText(this@SplashScreenActivity,"User already registered", Toast.LENGTH_SHORT).show()

                    }
                    else {
                            showRegisterLayout()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(this@SplashScreenActivity,p0.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun showRegisterLayout() {
        val builder = AlertDialog.Builder(this,R.style.DialogTheme)
        val itemView = LayoutInflater.from(this).inflate(R.layout.layout_register,null)

        val edit_first_name = itemView.findViewById<View>(R.id.edit_first_name) as TextInputEditText
        val edit_Last_name = itemView.findViewById<View>(R.id.edit_last_name) as TextInputEditText
        val edit_phone_number = itemView.findViewById<View>(R.id.edit_phone_number) as TextInputEditText

        val btn_continue = itemView.findViewById<View>(R.id.btn_register) as Button

        // Set data
        if(FirebaseAuth.getInstance().currentUser!!.phoneNumber != null &&
            !TextUtils.isDigitsOnly(FirebaseAuth.getInstance().currentUser!!.phoneNumber))

            edit_phone_number.setText(FirebaseAuth.getInstance().currentUser!!.phoneNumber)

        //View

        builder.setView(itemView)
        val dialog = builder.create()
        dialog.show()

        // Event

        btn_continue.setOnClickListener {
            if (TextUtils.isDigitsOnly(edit_first_name.text.toString())) {
                Toast.makeText(
                    this@SplashScreenActivity,
                    "Please enter first Name",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else if (TextUtils.isDigitsOnly(edit_Last_name.text.toString())) {
                Toast.makeText(
                    this@SplashScreenActivity,
                    "Please enter last Name",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else if (TextUtils.isDigitsOnly(edit_phone_number.text.toString())) {
                Toast.makeText(
                    this@SplashScreenActivity,
                    "Please enter Phone Number",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                val model = DriverInfoModel()
                model.firstName = edit_first_name.text.toString()
                model.lastName = edit_Last_name.text.toString()
                model.phoneNumber = edit_phone_number.text.toString()
                model.rating = 0.0

                driverInfoRef.child(FirebaseAuth.getInstance().currentUser!!.uid)
                    .setValue(model)
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this@SplashScreenActivity,
                            "" + e.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()

                    }
                    .addOnSuccessListener {
                        Toast.makeText(
                            this@SplashScreenActivity,
                            "Register Succesfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()

                    }

            }
        }


    }

    private fun showLoginLayout() {
        val authMethodPickerLayout = AuthMethodPickerLayout.Builder(R.layout.layout_sign_in)
            .setPhoneButtonId(R.id.btn_phone_sign_in)
            .setGoogleButtonId(R.id.btn_google_sign_in)
            .build();

        signInLauncher.launch(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAuthMethodPickerLayout(authMethodPickerLayout)
                .setTheme(R.style.LoginTheme)
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .build()
            )
    }
}