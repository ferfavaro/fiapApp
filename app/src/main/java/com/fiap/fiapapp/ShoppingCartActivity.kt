package com.fiap.fiapapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShoppingCartActivity : AppCompatActivity() {
    private lateinit var carImage: ImageView
    private lateinit var vehicleName: TextView
    private lateinit var onixDetails: LinearLayout
    private lateinit var poloDetails: LinearLayout
    private lateinit var onixImage: ImageView
    private lateinit var onixName: TextView
    private lateinit var poloImage: ImageView
    private lateinit var poloName: TextView

    private lateinit var tripPrice: TextView
    private lateinit var fare: TextView
    private lateinit var tax: TextView
    private lateinit var totalPrice: TextView
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        carImage = findViewById(R.id.carImage)
        vehicleName = findViewById(R.id.vehicleName)
        onixDetails = findViewById(R.id.onixDetails)
        poloDetails = findViewById(R.id.poloDetails)
        onixImage = findViewById(R.id.onixImage)
        onixName = findViewById(R.id.onixName)
        poloImage = findViewById(R.id.poloImage)
        poloName = findViewById(R.id.poloName)

        tripPrice = findViewById(R.id.tripPrice)
        fare = findViewById(R.id.fare)
        tax = findViewById(R.id.tax)
        totalPrice = findViewById(R.id.totalPrice)
        submitButton = findViewById(R.id.submitButton)

        // Receber dados do Intent
        val vehicleNameExtra = intent.getStringExtra("vehicleName") ?: "Hyundai HB20 - 2021"
        val tripPriceExtra = intent.getFloatExtra("tripPrice", 0f)
        val fareExtra = intent.getFloatExtra("fare", 0f)
        val taxExtra = intent.getFloatExtra("tax", 0f)

        // Definir informações do veículo
        setVehicleInfo(vehicleNameExtra)

        // Definir preços
        tripPrice.text = "Preço da Viagem: R$ $tripPriceExtra"
        fare.text = "Tarifa: R$ $fareExtra"
        tax.text = "Tributo: R$ $taxExtra"
        totalPrice.text = "Preço Total: R$ ${tripPriceExtra + fareExtra + taxExtra}"

        submitButton.setOnClickListener {
            // Lógica para confirmar a compra
        }
    }

    private fun setVehicleInfo(vehicleName: String) {
        when (vehicleName) {
            "Hyundai HB20 - 2021" -> {
                carImage.setImageResource(R.drawable.hb20)
                this.vehicleName.text = getString(R.string.hb20_name)
                onixDetails.visibility = View.GONE
                poloDetails.visibility = View.GONE
            }
            "Chevrolet Onix - 2021" -> {
                carImage.visibility = View.GONE
                onixDetails.visibility = View.VISIBLE
                onixImage.setImageResource(R.drawable.onix)
                onixName.text = getString(R.string.onix_name)
                poloDetails.visibility = View.GONE
            }
            "Volkswagen Polo - 2021" -> {
                carImage.visibility = View.GONE
                onixDetails.visibility = View.GONE
                poloDetails.visibility = View.VISIBLE
                poloImage.setImageResource(R.drawable.polo)
                poloName.text = getString(R.string.polo_name)
            }
        }
    }
}
