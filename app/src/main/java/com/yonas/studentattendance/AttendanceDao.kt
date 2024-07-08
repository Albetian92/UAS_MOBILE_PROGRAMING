package com.yonas.studentattendance
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AttendanceDao {
    @Insert
    suspend fun insert(attendance: AttendanceEntity)

    @Query("SELECT * FROM attendance WHERE studentId = :studentId")
    suspend fun getAttendance(studentId: String): List<AttendanceEntity>
}
