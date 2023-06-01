package com.chiller3.bcr

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chiller3.bcr.settings.SettingsActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val demo: TextView = findViewById(R.id.demo)

        demo.setOnTouchListener(object : View.OnTouchListener {
            var handler: Handler = Handler()
            var numberOfTaps = 0
            var lastTapTimeMs: Long = 0
            var touchDownMs: Long = 0
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> touchDownMs = System.currentTimeMillis()
                    MotionEvent.ACTION_UP -> {
                        // Handle the numberOfTaps
                        handler.removeCallbacksAndMessages(null)
                        if (System.currentTimeMillis() - touchDownMs
                            > ViewConfiguration.getTapTimeout()
                        ) {
                            //it was not a tap
                            numberOfTaps = 0
                            lastTapTimeMs = 0
                        }
                        if (numberOfTaps > 0
                            && System.currentTimeMillis() - lastTapTimeMs
                            < ViewConfiguration.getDoubleTapTimeout()
                        ) {
                            // if the view was clicked once
                            numberOfTaps += 1
                        } else {
                            // if the view was never clicked
                            numberOfTaps = 1
                        }

                        lastTapTimeMs = System.currentTimeMillis()


                        // Five Taps

                        if (numberOfTaps == 5) {
                            val intent =
                                Intent(this@MainActivity, SettingsActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
                return true
            }
        })
    }


}