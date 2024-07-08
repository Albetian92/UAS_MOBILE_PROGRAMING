package com.yonas.studentattendance

import java.util.Date

data class Attendance(
    val studentId: String,
    val date: Date,
    val status: String
)