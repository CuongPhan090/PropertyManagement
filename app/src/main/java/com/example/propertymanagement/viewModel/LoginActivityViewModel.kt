package com.example.propertymanagement.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.model.mainRepository.LoginRequestData
import com.example.propertymanagement.model.mainRepository.LoginResponseData
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