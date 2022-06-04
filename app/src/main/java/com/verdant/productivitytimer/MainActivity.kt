package com.verdant.productivitytimer

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var timer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer = findViewById(R.id.timer)

        timer.setOnClickListener {
            AlertDialog.Builder(this, R.style.Theme_ProductivityTimer)
                .setTitle("Set timer")
                .setView(LayoutInflater.from(this).inflate(R.layout.picker, null, false))
                .setPositiveButton("Confirm") { _, _ ->
                    Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }
    }
}
