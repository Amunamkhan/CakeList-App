package com.droident.cakelistapp.data.remote

import com.droident.cakelistapp.data.remote.dto.CakeDto
import retrofit2.http.GET


interface CakeApi {
    //Todo it should be proper endpoint
    @GET("t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/waracle_cake-android-client")
    suspend fun getCakes():List<CakeDto>

}