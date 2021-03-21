package com.example.habitandgoaltracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

//Početni screen, dočeka nas logo aplikacije i dugme za prelazak na Glavni menu -> Main Activity

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_log)

        val button=findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}