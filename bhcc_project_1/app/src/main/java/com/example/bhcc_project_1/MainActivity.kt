package com.example.bhcc_project_1

import android.R.attr.button
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sub = findViewById<Button>(R.id.subtract)
        sub.setOnClickListener(View.OnClickListener {
            // Code here executes on main thread after user presses button
            println("hello world")
        })
    }
}