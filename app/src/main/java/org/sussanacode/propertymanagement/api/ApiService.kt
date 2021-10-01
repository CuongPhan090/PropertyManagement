package org.sussanacode.propertymanagement.api

import org.sussanacode.propertymanagement.entity.request.PropertyRequestData
import org.sussanacode.propertymanagement.entity.response.PropertyResponseData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Content-type: application/json")
    @POST("property")
    fun postProperty(@Body propertyData: PropertyRequestData): Call<PropertyResponseData>


    @GET("property")
    fun getProperty(): Call<PropertyResponseData>

   // https://apolis-property-management.herokuapp.com/api/property/user/%2260a2d28b62e1ec001788a646%22

    @GET("property/user/{id}")
    fun getPropertyByUserID(@Path("id") id:String ): Call<PropertyResponseData>
}