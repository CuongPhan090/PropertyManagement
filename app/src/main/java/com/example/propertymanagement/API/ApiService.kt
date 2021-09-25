package com.example.propertymanagement.API

import com.example.propertymanagement.repository.mainRepository.LoginRequestData
import com.example.propertymanagement.repository.mainRepository.LoginResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-type: application/json")
    @POST("auth/login")
    fun login(@Body requestData: LoginRequestData): Call<LoginResponseData>

}