package com.yonas.studentattendance

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("students")
    fun getStudents(): Call<List<Student>>

    @POST("attendance")
    fun postAttendance(@Body attendance: Attendance): Call<Void>
}