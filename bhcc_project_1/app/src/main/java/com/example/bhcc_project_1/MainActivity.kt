package com.example.bhcc_project_1

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var currentCount = 0
    var gotoCount    = 6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act)

        val tview = findViewById<View>(R.id.currentCount) as TextView
        tview.setText(currentCount)
        val sub = findViewById<Button>(R.id.subtract)
        sub.setOnClickListener(View.OnClickListener {
            // Code here executes on main thread after user presses button
            currentCount--
            tview.setText(currentCount)
            /*if (currentCount == gotoCount) {
                // start the new activity
                val i = Intent(this, MainActivity2::class.java)
                i.putExtra("gotoVal", gotoCount)
                startActivity(i)
            }*/

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                val returnString = data.getStringExtra("keyName")

                // Set text view with string
                val textView = findViewById<View>(R.id.textView) as TextView
                textView.text = returnString
            }
        }
    }
}