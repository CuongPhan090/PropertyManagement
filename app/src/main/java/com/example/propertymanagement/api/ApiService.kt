package com.example.propertymanagement.api

import com.example.propertymanagement.model.mainRepository.LoginRequestData
import com.example.propertymanagement.model.mainRepository.LoginResponseData
import com.example.propertymanagement.model.mainRepository.RegisterRequestData
import com.example.propertymanagement.model.mainRepository.RegisterResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-type: application/json")
    @POST("auth/login")
    fun login(@Body requestData: LoginRequestData): Call<LoginResponseData>

    @Headers("Content-type: application/json")
    @POST("auth/register")
    fun register(@Body requestData: RegisterRequestData): Call<RegisterResponseData>

}