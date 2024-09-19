package com.fiap.fiapapp.Model

data class DriverInfoModel(
     var firstName: String = "",
     var lastName: String = "",
     var email: String = "", // New email field
     var phoneNumber: String = "",
     var rating: Double = 0.0
)
