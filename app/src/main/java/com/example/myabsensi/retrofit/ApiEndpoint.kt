package com.example.myabsensi.retrofit

import com.example.myabsensi.pojo.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<LoginResponse>

    @GET("DashboardController-index")
    fun dashboard(
        @Header("Authorization") token:String
    ):Call<DashboardResponse>

    @GET("logout")
    fun logout(
        @Header("Authorization") token:String
    ):Call<ResponseBody>
}