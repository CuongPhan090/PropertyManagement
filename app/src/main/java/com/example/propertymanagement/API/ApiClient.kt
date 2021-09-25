package com.example.propertymanagement.API

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val myRetrofit: Retrofit by lazy{
        val loggingInterceptor = HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val r = Retrofit.Builder()
            .baseUrl("https://apolis-property-management.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
        r
    }

    val apiService: ApiService by lazy{
        myRetrofit.create(ApiService::class.java)
    }
}