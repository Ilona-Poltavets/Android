package com.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class UsersDBHendler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "mobilDB"
        private val TABLE_CONTACTS = "users"
        private val COLUMN_ID = "id"
        private val COLUMN_NAME = "name"
        private val COLUMN_EMAIL = "email"
        private val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_EMAIL + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data
    fun addUser(user: UsersModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //contentValues.put(COLUMN_ID, user.id)
        contentValues.put(COLUMN_NAME, user.name)
        contentValues.put(COLUMN_EMAIL, user.email)
        contentValues.put(COLUMN_PASSWORD, user.password)
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }

    //method to read data
    @SuppressLint("Range")
    fun viewUsers(): List<UsersModel> {
        val userList: ArrayList<UsersModel> = ArrayList<UsersModel>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var email: String
        var password: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                password = cursor.getString(cursor.getColumnIndex("password"))
                val user = UsersModel(id = id, name = name, email = email,password = password)
                userList.add(user)
            } while (cursor.moveToNext())
        }
        return userList
    }

    //method to delete data
    fun deleteEmployee(user: UsersModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, user.id)
        val success = db.delete(TABLE_CONTACTS, "id=" + user.id, null)
        db.close()
        return success
    }
}
