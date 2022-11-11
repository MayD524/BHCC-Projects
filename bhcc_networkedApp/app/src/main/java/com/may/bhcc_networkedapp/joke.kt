package com.may.bhcc_networkedapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors


class joke : AppCompatActivity() {

    fun getData(uri: String): String {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                123
            )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                123
            )
            return "Permission Denied"
        } else {
            //Asynctask to create a thread to downlaod image in the background
            val url: URL

            try {
                url = URL(uri)
            } catch (e: MalformedURLException) {
                return "Could not tell a good joke.. Malformed URL"
            }

            return try {
                url.readText()
            } catch(e: IOException) {

                "Could not tell a good joke.. IOException"
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)

        findViewById<Button>(R.id.home_joke).setOnClickListener() {
            val output = Intent(this, MainActivity::class.java)
            setResult(RESULT_OK, output)
            finish()
        }

        val txt = findViewById<TextView>(R.id.joke_text)
        Executors.newSingleThreadExecutor().execute {
            Looper.prepare()
            try {
                txt.text = getData("https://v2.jokeapi.dev/joke/Programming?format=txt")

            } catch (e: Exception){
                txt.text = e.message
            }
        }

    }
}