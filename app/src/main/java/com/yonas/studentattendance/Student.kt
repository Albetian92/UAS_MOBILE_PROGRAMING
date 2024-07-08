package com.yonas.studentattendance

data class Student(
    val studentId: String,
    val name: String,
    val grade: Int,
    val address: String? = null // contoh atribut tambahan, opsional
)