package com.example.propertymanagement.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.model.mainRepository.RegisterRequestData
import com.example.propertymanagement.model.mainRepository.RegisterResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivityViewModel : ViewModel() {

    var error: MutableLiveData<String> = MutableLiveData()
    var registerResponse: MutableLiveData<RegisterResponseData> = MutableLiveData()

    fun register(userInfo: RegisterRequestData) {
        val call: Call<RegisterResponseData> = ApiClient.apiService.register(userInfo)
        call.enqueue(object : Callback<RegisterResponseData> {
            override fun onResponse(
                call: Call<RegisterResponseData>,
                response: Response<RegisterResponseData>
            ) {
                if (!response.isSuccessful) {
                    error.postValue("We cannot complete the register process right now, please come back later.")
                    return
                }
                registerResponse.postValue(response.body())
            }
            override fun onFailure(call: Call<RegisterResponseData>, t: Throwable) {
                error.postValue("Unable to send register request, please try again")
            }
        })
    }
}