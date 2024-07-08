package com.yonas.studentattendance

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StudentDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_STUDENTS)
        db.execSQL(CREATE_TABLE_ATTENDANCE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ATTENDANCE")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "studentAttendance.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_STUDENTS = "students"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"

        const val TABLE_ATTENDANCE = "attendance"
        const val COLUMN_ATTENDANCE_ID = "attendance_id"
        const val COLUMN_STUDENT_ID = "student_id"
        const val COLUMN_DATE = "date"
        const val COLUMN_PHOTO_PATH = "photo_path"

        private const val CREATE_TABLE_STUDENTS = "CREATE TABLE $TABLE_STUDENTS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT)"

        private const val CREATE_TABLE_ATTENDANCE = "CREATE TABLE $TABLE_ATTENDANCE (" +
                "$COLUMN_ATTENDANCE_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_STUDENT_ID INTEGER, " +
                "$COLUMN_DATE TEXT, " +
                "$COLUMN_PHOTO_PATH TEXT, " +
                "FOREIGN KEY($COLUMN_STUDENT_ID) REFERENCES $TABLE_STUDENTS($COLUMN_ID))"
    }
}