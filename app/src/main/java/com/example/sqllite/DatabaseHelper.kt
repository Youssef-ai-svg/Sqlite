package com.example.sqllite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Nom_De_la_bade_de_dades"
        private const val TABLE_NAME = "Nom_de_la_taula"
        private const val CREATE_TABLE_QUERY = "CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY, name TEXT)"
    }

    @SuppressLint("Range")
    fun getAllData(): List<String> {
        val dataList = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                dataList.add(name)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return dataList
    }

    fun insertData(name: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", name)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getOneName(name : String) : List<String> {
        val db = readableDatabase
        val lista = mutableListOf<String>()
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE name = '$name'", null)
        if (cursor.moveToFirst()) {
            do {
                val Nombre = cursor.getString(cursor.getColumnIndex("name"))
                lista.add(Nombre)
                println(name)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }
}

