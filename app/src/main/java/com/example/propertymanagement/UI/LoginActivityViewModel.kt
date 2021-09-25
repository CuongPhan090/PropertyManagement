package com.example.propertymanagement.UI

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.API.ApiClient
import com.example.propertymanagement.repository.mainRepository.LoginRequestData
import com.example.propertymanagement.repository.mainRepository.LoginResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityViewModel : ViewModel() {

    val loginResponse = MutableLiveData<LoginResponseData>()
    val error = MutableLiveData<String>()

    fun login(email: String, password: String) {
        val userInfo = LoginRequestData(email, password)
        val call: Call<LoginResponseData> = ApiClient.apiService.login(userInfo)

        call.enqueue(object : Callback<LoginResponseData> {
            override fun onResponse(
                call: Call<LoginResponseData>,
                response: Response<LoginResponseData>
            ) {

                if (!response.isSuccessful) {
                    error.postValue("Invalid username/password, please try again!")
                    return
                }
                loginResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                error.postValue("Unable to login, please try again!")
            }
        })
    }
}