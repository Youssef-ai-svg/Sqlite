package com.example.sqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name = findViewById<EditText>(R.id.Name)
        val dbHelper = DatabaseHelper(this)

        findViewById<TextView>(R.id.Insert).setOnClickListener {
            dbHelper.insertData(name.text.toString())
        }
        findViewById<TextView>(R.id.Show).setOnClickListener() {
            //val dataList = dbHelper.getAllData()
            val dataList = dbHelper.getOneName(name.text.toString())
            findViewById<TextView>(R.id.result).text = dataList.joinToString("\n")
        }
    }
}