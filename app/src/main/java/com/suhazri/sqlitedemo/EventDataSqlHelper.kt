package com.suhazri.sqlitedemo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class EventDataSqlHelper(context: Context?) : SQLiteOpenHelper(context,
    EventDataSqlHelper.DATABASE_NAME, null,
    EventDataSqlHelper.DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "events.db"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "users"
        val COL_NAME = "name"
        val COL_EMAIL = "email"
        val COL_PHONE = "phone"
    }

    //When DB is created for the first time this method is called
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME " +
                "(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_NAME TEXT, $COL_EMAIL TEXT, $COL_PHONE TEXT)"
        p0?.execSQL(query)
    }

    //When DB is upgraded this method is called
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}
