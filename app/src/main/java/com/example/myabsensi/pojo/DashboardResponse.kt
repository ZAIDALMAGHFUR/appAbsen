package com.example.myabsensi.pojo

data class DashboardResponse(
    val status : String,
    val meta : User,
    val data : User,
    val message : String,
) {
}
