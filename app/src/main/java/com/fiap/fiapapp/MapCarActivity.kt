package com.fiap.fiapapp



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.material.textfield.TextInputEditText

class MapCarActivity : AppCompatActivity() {
    private lateinit var carImage: ImageView
    private lateinit var carTitle: TextView
    private lateinit var carRating: TextView
    private lateinit var rentButton: Button
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
        carImage = findViewById(R.id.carImage)
        carTitle = findViewById(R.id.carTitle)
        carRating = findViewById(R.id.carRating)
        rentButton = findViewById(R.id.rentButton)
        filterButton = findViewById(R.id.filterButton)
        listButton = findViewById(R.id.listButton)
        searchInput = findViewById(R.id.searchInput)
        filterDialog = findViewById(R.id.filterDialog)
        distanceRadioGroup = findViewById(R.id.distanceRadioGroup)

        // Initially hide the car image and rent button
        carImage.isVisible = false
        carTitle.isVisible = false
        carRating.isVisible = false
        rentButton.isVisible = false

        // Handle renting logic
        rentButton.setOnClickListener {
            handleRent()
        }

        // Handle filtering logic
        filterButton.setOnClickListener {
            toggleFilter()
        }

        // Show car image and "Alugar" button on listButton click
        listButton.setOnClickListener {
            carImage.isVisible = true
            carTitle.isVisible = true
            carRating.isVisible = true
            rentButton.isVisible = true
        }

        // Observe changes in filter visibility
        viewModel.isFilterVisible.observe(this) { isVisible: Boolean ->
            filterDialog.isVisible = isVisible
        }
    }

    // Function to handle the rent button press
    private fun handleRent() {
        val intent = Intent(this, ShoppingCartActivity::class.java)
        intent.putExtra("vehicleName", "Hyundai HB20 - 2021")
        intent.putExtra("tripPrice", 35)
        intent.putExtra("fare", 20)
        intent.putExtra("tax", 10)
        startActivity(intent)
    }

    // Function to toggle the filter visibility
    private fun toggleFilter() {
        viewModel.toggleFilterVisibility()
    }

    // Function to apply filters based on user selection
    private fun applyFilter() {
        val selectedDistanceId = distanceRadioGroup.checkedRadioButtonId
        val selectedDistance = findViewById<RadioButton>(selectedDistanceId).text.toString()

        // Logic to filter the results based on the selected distance
        viewModel.setDistanceFilter(selectedDistance)

        // Dismiss the filter dialog
        toggleFilter()
    }
}
