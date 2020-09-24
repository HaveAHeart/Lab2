package com.example.lab2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.continuewatch.*

class ContinueWatch : AppCompatActivity() {
    var secondsElapsed: Int = 0
    var bgThreadActive: Boolean = true
    var backgroundThread = Thread {
        while (true) {
            if (bgThreadActive) secondsElapsed++
            textSecondsElapsed.post {
                textSecondsElapsed.text = String.format("Seconds elapsed: %s", secondsElapsed)
            }
            Thread.sleep(1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.continuewatch)
        bgThreadActive = true
        backgroundThread.start()
    }

    override fun onPause() {
        super.onPause()
        bgThreadActive = false
    }

    override fun onResume() {
        super.onResume()
        bgThreadActive = true
    }

    override fun onStop() {
        super.onStop()
        bgThreadActive = false
    }

    override fun onRestart() {
        super.onRestart()
        bgThreadActive = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SECONDS, secondsElapsed)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        secondsElapsed = savedInstanceState.getInt(SECONDS)
    }

    companion object { const val SECONDS = "secondsElapsed" }
}