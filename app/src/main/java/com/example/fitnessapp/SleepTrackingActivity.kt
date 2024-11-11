package com.example.fitnessapp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SleepTrackingActivity : AppCompatActivity() {

    private lateinit var sleepStatusText: TextView
    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var endButton: Button
    private lateinit var lastSleepTimeText: TextView
    private val handler = Handler(Looper.getMainLooper())
    private var startTime: Long = 0
    private var isTracking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_tracking)

        sleepStatusText = findViewById(R.id.sleepStatusText)
        timerText = findViewById(R.id.timerText)
        startButton = findViewById(R.id.startTrackingButton)
        endButton = findViewById(R.id.endTrackingButton)
        lastSleepTimeText = findViewById(R.id.lastSleepTimeText)

        val sharedPreferences = getSharedPreferences("sleep_tracking_prefs", Context.MODE_PRIVATE)
        startTime = sharedPreferences.getLong("start_time", 0)

        // Display last sleep duration if exists
        val lastSleepDuration = sharedPreferences.getLong("last_sleep_duration", 0)
        if (lastSleepDuration != 0L) {
            val hours = (lastSleepDuration / (1000 * 60 * 60))
            val minutes = (lastSleepDuration / (1000 * 60)) % 60
            val seconds = (lastSleepDuration / 1000) % 60
            lastSleepTimeText.text = String.format("Last Sleep Duration: %02d:%02d:%02d", hours, minutes, seconds)
        } else {
            lastSleepTimeText.text = "Last Sleep Duration: Not Recorded"
        }

        if (startTime != 0L) {
            isTracking = true
            updateTimer()
        }

        startButton.setOnClickListener {
            if (!isTracking) {
                startTime = System.currentTimeMillis()
                sharedPreferences.edit().putLong("start_time", startTime).apply()
                sleepStatusText.text = "Sleep Tracking Status: Activated"
                isTracking = true
                updateTimer()
            }
        }

        endButton.setOnClickListener {
            if (isTracking) {
                val sleepDuration = System.currentTimeMillis() - startTime
                sharedPreferences.edit().putLong("last_sleep_duration", sleepDuration).apply()

                // Save the end time
                sharedPreferences.edit().remove("start_time").apply()

                sleepStatusText.text = "Sleep Tracking Status: Not Activated"
                timerText.text = "Sleep Duration: 00:00"
                handler.removeCallbacksAndMessages(null)
                isTracking = false

                // Update last sleep time display
                val hours = (sleepDuration / (1000 * 60 * 60))
                val minutes = (sleepDuration / (1000 * 60)) % 60
                val seconds = (sleepDuration / 1000) % 60
                lastSleepTimeText.text = String.format("Last Sleep Duration: %02d:%02d:%02d", hours, minutes, seconds)
            }
        }
    }

    private fun updateTimer() {
        handler.post(object : Runnable {
            override fun run() {
                if (isTracking) {
                    val currentTime = System.currentTimeMillis()
                    val elapsedTime = currentTime - startTime
                    val seconds = (elapsedTime / 1000) % 60
                    val minutes = (elapsedTime / (1000 * 60)) % 60
                    val hours = (elapsedTime / (1000 * 60 * 60))

                    timerText.text = String.format("Sleep Duration: %02d:%02d:%02d", hours, minutes, seconds)
                    handler.postDelayed(this, 1000)
                }
            }
        })
    }
}
