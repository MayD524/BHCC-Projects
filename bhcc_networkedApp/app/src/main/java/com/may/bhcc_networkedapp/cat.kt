package com.may.bhcc_networkedapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors


class cat : AppCompatActivity() {

    fun getData(uri: String) : String {
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
            val url : URL

            try {
                url = URL(uri)
            } catch (e: MalformedURLException) {
                return "Could not tell a good joke.. Malformed URL"
            }

            return try {
                val inp = url.readText().replace("[", "").replace("]", "")
                val reader = JSONObject(inp)
                reader.get("url") as String
            } catch(e: IOException) {

                "Could not tell get cat.. IOException"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat)

        findViewById<Button>(R.id.cat_home).setOnClickListener() {
            val output = Intent(this, MainActivity::class.java)
            setResult(RESULT_OK, output)
            finish()
        }

        val img = findViewById<ImageView>(R.id.cat_image)
        val errText = findViewById<TextView>(R.id.ErrorText)
        Executors.newSingleThreadExecutor().execute {
            Looper.prepare()
            try {
                val nextUrl = getData("https://api.thecatapi.com/v1/images/search")
                val data = URL(nextUrl).openStream()
                val bitmap = BitmapFactory.decodeStream(data)
                img.setImageBitmap(bitmap)
            } catch (e: java.lang.Exception) {
                errText.text = ""
            }
        }

    }
}