package com.example.bhcc_project_1

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private final val SECOND_ACTIVITY_REQUEST_CODE = 0
    private var gotoCount = 6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)

        var currentCount = 0


        val tview = findViewById<View>(R.id.countText) as TextView
        tview.text = currentCount.toString()

        val add = findViewById<Button>(R.id.add).also {
            it.setOnClickListener(View.OnClickListener {
                currentCount ++
                tview.text = currentCount.toString()

                if (currentCount == gotoCount) {
                    val i = Intent(this, MainActivity2::class.java)
                    i.putExtra("gotoVal", gotoCount)
                    startActivityForResult(i, SECOND_ACTIVITY_REQUEST_CODE)
                }
            })
        }

        val sub = findViewById<Button>(R.id.subtract).also {
            it.setOnClickListener(View.OnClickListener {
                // Code here executes on main thread after user presses button
                currentCount--
                tview.text = currentCount.toString()
                if (currentCount == gotoCount) {
                    // start the new activity
                    val i = Intent(this, MainActivity2::class.java)
                    i.putExtra("gotoVal", gotoCount)
                    startActivityForResult(i, SECOND_ACTIVITY_REQUEST_CODE)
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            gotoCount = data.getIntExtra("gotoVal", 0)
        }
    }
}