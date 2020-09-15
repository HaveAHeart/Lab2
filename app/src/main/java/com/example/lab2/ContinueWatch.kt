package com.example.lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.continuewatch.*

class ContinueWatch : AppCompatActivity() {
    var secondsElapsed: Int = 0

    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            textSecondsElapsed.post {
                textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            with(savedInstanceState) { secondsElapsed = getInt(SECONDS) }
        }

        setContentView(R.layout.continuewatch)
        textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
        backgroundThread.start()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putInt(SECONDS, secondsElapsed) }
        super.onSaveInstanceState(outState)
    }

    companion object { const val SECONDS = "secondsElapsed" }
}