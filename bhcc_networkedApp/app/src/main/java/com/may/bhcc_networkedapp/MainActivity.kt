package com.may.bhcc_networkedapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    val textFile = "last_selected.txt"
    var last_app = ""

    private fun update_text(txtView: TextView, text: String, file: File) {
        writeFile(file, text)
        txtView.text = text
    }

    private fun createFile(filename: String, isPersistant :Boolean): File {

        val file: File = if (isPersistant) {
            File(this.filesDir, filename)
        } else {
            File(this.cacheDir, filename)
        }

        if (!file.exists()) {
            return try {
                file.createNewFile()
                file.writeText("")
                Toast.makeText(this, "$filename has been created", Toast.LENGTH_SHORT).show()
                file
            } catch(e: IOException) {
                Toast.makeText(this, "$filename could not create file", Toast.LENGTH_SHORT).show()
                file
            }
        }
        return file
    }

    private fun writeFile(file: File, data: String) {
        if (!file.canWrite()) {
            Toast.makeText(this, "${file.name} is not writable.", Toast.LENGTH_SHORT).show()
            return
        }

        file.writeText(data)
    }

    private fun readFile(file: File): String {
        val output: String
        val inputStream: InputStream

        if (file.exists()) {
            return file.readText()
        }
        return ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val workingFile = createFile(textFile, true)
        val txtView = findViewById<TextView>(R.id.last_selected)

        val last = readFile(workingFile)
        txtView.text = last


        var jokeButton = findViewById<Button>(R.id.joke_button);
        jokeButton.setOnClickListener() {
            Toast.makeText(this, "tell me a joke!", Toast.LENGTH_SHORT).show()
            last_app = "You last asked for a joke"
            update_text(txtView, last_app, workingFile)
            val it = Intent(this, joke::class.java)
            startActivity(it)
        }

        var dog_button = findViewById<Button>(R.id.dog_button)
        dog_button.setOnClickListener() {
            Toast.makeText(this, "Show me a dog!", Toast.LENGTH_SHORT).show()
            last_app = "You last asked for a picture of a dog"
            update_text(txtView, last_app, workingFile)
            val it = Intent(this, dog::class.java)
            startActivity(it)
        }

        var cat_button = findViewById<Button>(R.id.cat_button)
        cat_button.setOnClickListener() {
            Toast.makeText(this, "Show me a cat!", Toast.LENGTH_SHORT).show()
            last_app = "You last asked for a picture of a cat"
            update_text(txtView, last_app, workingFile)
            val it = Intent(this, cat::class.java)
            startActivity(it)
        }

    }
}