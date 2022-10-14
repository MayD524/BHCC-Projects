package com.example.bhcc_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val setValbtn = findViewById<Button>(R.id.btnSetNewVal).also {
            it.setOnClickListener(View.OnClickListener {
                val valSetter = findViewById<EditText>(R.id.editTextNumberSigned).text.toString().toInt()
                val output = Intent(this, MainActivity::class.java)
                output.putExtra("gotoVal", valSetter)
                setResult(RESULT_OK, output)
                finish()
            })

        }

    }
}