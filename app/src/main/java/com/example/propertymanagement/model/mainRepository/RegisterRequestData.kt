package com.example.propertymanagement.model.mainRepository

data class RegisterRequestData(
    val email: String,
    val name: String,
    val password: String,
    val type: String,
    val landlordEmail: String?
)