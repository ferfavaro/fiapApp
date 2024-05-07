package com.fiap.fiapapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fiap.fiapapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ClientHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)

        val button: Button = findViewById(R.id.button_text)

        button.setOnClickListener {
            button.text = "Texto alterado!"
            Snackbar.make(it, "Texto alterado!", Snackbar.LENGTH_SHORT).show()
        }
    }}