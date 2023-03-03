package com.example.myabsensi.pojo

data class LoginResponse(
    val status : String,
    val meta : User,
    val data : User,
    val message : String,
) {
}
