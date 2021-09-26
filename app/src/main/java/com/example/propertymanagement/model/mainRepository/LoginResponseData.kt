package com.example.propertymanagement.model.mainRepository

data class LoginResponseData(
    val token: String?,
    val user: User?,
    val error: String?,
    val message: String?
)

data class User(
    val _id: String?,
    val email: String?,
    val landlordEmail: String?,
    val name: String?,
    val password: String?,
    val type: String?
)