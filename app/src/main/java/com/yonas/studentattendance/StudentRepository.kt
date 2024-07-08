package com.yonas.studentattendance

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class StudentRepository<Student>(context: Context) {

    private val dbHelper: StudentDatabaseHelper = StudentDatabaseHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun insertStudent(name: String): Long {
        val values = ContentValues().apply {
            put(StudentDatabaseHelper.COLUMN_NAME, name)
        }
        return db.insert(StudentDatabaseHelper.TABLE_STUDENTS, null, values)
    }

    fun insertAttendance(studentId: Long, date: String, photoPath: String): Long {
        val values = ContentValues().apply {
            put(StudentDatabaseHelper.COLUMN_STUDENT_ID, studentId)
            put(StudentDatabaseHelper.COLUMN_DATE, date)
            put(StudentDatabaseHelper.COLUMN_PHOTO_PATH, photoPath)
        }
        return db.insert(StudentDatabaseHelper.TABLE_ATTENDANCE, null, values)
    }

    fun getStudents(): List<Student> {
        val students = mutableListOf<Student>()
        val cursor: Cursor = db.query(
            StudentDatabaseHelper.TABLE_STUDENTS,
            null, null, null, null, null, null
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_NAME))
                students.add(Student(id, name))
            }
        }
        cursor.close()
        return students
    }

    fun getAttendances(): List<Attendance> {
        val attendances = mutableListOf<Attendance>()
        val cursor: Cursor = db.query(
            StudentDatabaseHelper.TABLE_ATTENDANCE,
            null, null, null, null, null, null
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_ATTENDANCE_ID))
                val studentId = getLong(getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_STUDENT_ID))
                val date = getString(getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_DATE))
                val photoPath = getString(getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_PHOTO_PATH))
                attendances.add(Attendance(id, studentId, date, photoPath))
            }
        }
        cursor.close()
        return attendances
    }
}