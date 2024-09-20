package com.fiap.fiapapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class MapCarActivity : AppCompatActivity() {
    private lateinit var backgroundImage: ImageView
    private lateinit var carImage: ImageView
    private lateinit var carTitle: TextView
    private lateinit var carRating: TextView
    private lateinit var rentButton: Button

    private lateinit var onixImage: ImageView
    private lateinit var onixTitle: TextView
    private lateinit var onixRating: TextView
    private lateinit var onixRentButton: Button

    private lateinit var poloImage: ImageView
    private lateinit var poloTitle: TextView
    private lateinit var poloRating: TextView
    private lateinit var poloRentButton: Button

    private lateinit var filterButton: Button
    private lateinit var listButton: Button
    private lateinit var searchInput: TextInputEditText
    private lateinit var distanceRadioGroup: RadioGroup
    private lateinit var filterDialog: ConstraintLayout

    private lateinit var viewModel: MapCarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_car)

        // Initialize the view model
        viewModel = ViewModelProvider(this).get(MapCarViewModel::class.java)

        // Initialize UI components
        backgroundImage = findViewById(R.id.backgroundImage)
        carImage = findViewById(R.id.carImage)
        carTitle = findViewById(R.id.carTitle)
        carRating = findViewById(R.id.carRating)
        rentButton = findViewById(R.id.rentButton)

        onixImage = findViewById(R.id.onixImage)
        onixTitle = findViewById(R.id.onixTitle)
        onixRating = findViewById(R.id.onixRating)
        onixRentButton = findViewById(R.id.onixRentButton)

        poloImage = findViewById(R.id.poloImage)
        poloTitle = findViewById(R.id.poloTitle)
        poloRating = findViewById(R.id.poloRating)
        poloRentButton = findViewById(R.id.poloRentButton)

        filterButton = findViewById(R.id.filterButton)
        listButton = findViewById(R.id.listButton)
        searchInput = findViewById(R.id.searchInput)
        filterDialog = findViewById(R.id.filterDialog)
        distanceRadioGroup = findViewById(R.id.distanceRadioGroup)

        // Initially hide all car images and rent buttons
        hideAllCars()

        // Handle renting logic
        rentButton.setOnClickListener { handleRent("Hyundai HB20 - 2021") }
        onixRentButton.setOnClickListener { handleRent("Chevrolet Onix - 2021") }
        poloRentButton.setOnClickListener { handleRent("Volkswagen Polo - 2021") }

        // Show car images and rent buttons on listButton click
        listButton.setOnClickListener { showCars() }

        // Observe changes in filter visibility
        viewModel.isFilterVisible.observe(this) { isVisible: Boolean ->
            filterDialog.isVisible = isVisible
        }
    }

    // Function to hide all cars
    private fun hideAllCars() {
        carImage.isVisible = false
        carTitle.isVisible = false
        carRating.isVisible = false
        rentButton.isVisible = false

        onixImage.isVisible = false
        onixTitle.isVisible = false
        onixRating.isVisible = false
        onixRentButton.isVisible = false

        poloImage.isVisible = false
        poloTitle.isVisible = false
        poloRating.isVisible = false
        poloRentButton.isVisible = false

        // Hide background image if no cars are visible
        backgroundImage.isVisible = true // Mantenha o fundo visível
    }

    // Function to show cars
    private fun showCars() {
        carImage.isVisible = true
        carTitle.isVisible = true
        carRating.isVisible = true
        rentButton.isVisible = true

        onixImage.isVisible = true
        onixTitle.isVisible = true
        onixRating.isVisible = true
        onixRentButton.isVisible = true

        poloImage.isVisible = true
        poloTitle.isVisible = true
        poloRating.isVisible = true
        poloRentButton.isVisible = true

        // Hide background image if cars are visible
        backgroundImage.isVisible = false
    }

    // Function to handle the rent button press
    private fun handleRent(vehicleName: String) {
        val intent = Intent(this, ShoppingCartActivity::class.java)
        intent.putExtra("vehicleName", vehicleName)
        intent.putExtra("tripPrice", 35) // Adjust as needed
        intent.putExtra("fare", 20) // Adjust as needed
        intent.putExtra("tax", 10) // Adjust as needed
        startActivity(intent)
    }

    // Function to toggle the filter visibility
    private fun toggleFilter() {
        viewModel.toggleFilterVisibility()
    }
}
