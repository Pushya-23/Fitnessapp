package com.example.fitnessapp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MuscleActivationActivity : AppCompatActivity() {

    private lateinit var muscleStatusText: TextView
    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var endButton: Button
    private val handler = Handler(Looper.getMainLooper())
    private var startTime: Long = 0
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_muscle_activation)

        muscleStatusText = findViewById(R.id.muscleStatusText)
        timerText = findViewById(R.id.timerText)
        startButton = findViewById(R.id.startActivationButton)
        endButton = findViewById(R.id.endActivationButton)

        val sharedPreferences = getSharedPreferences("muscle_activation_prefs", Context.MODE_PRIVATE)
        startTime = sharedPreferences.getLong("start_time", 0)

        if (startTime != 0L) {
            isRunning = true
            updateTimer()
        }

        startButton.setOnClickListener {
            if (!isRunning) {
                startTime = System.currentTimeMillis()
                sharedPreferences.edit().putLong("start_time", startTime).apply()
                muscleStatusText.text = "Muscle Activation Status: Activated"
                isRunning = true
                updateTimer()
            }
        }

        endButton.setOnClickListener {
            if (isRunning) {
                sharedPreferences.edit().remove("start_time").apply()
                muscleStatusText.text = "Muscle Activation Status: Not Activated"
                timerText.text = "Activation Time: 00:00"
                handler.removeCallbacksAndMessages(null)
                isRunning = false
            }
        }
    }

    private fun updateTimer() {
        handler.post(object : Runnable {
            override fun run() {
                if (isRunning) {
                    val currentTime = System.currentTimeMillis()
                    val elapsedTime = currentTime - startTime
                    val seconds = (elapsedTime / 1000) % 60
                    val minutes = (elapsedTime / (1000 * 60)) % 60
                    val hours = (elapsedTime / (1000 * 60 * 60))

                    timerText.text = String.format("Activation Time: %02d:%02d:%02d", hours, minutes, seconds)
                    handler.postDelayed(this, 1000)
                }
            }
        })
    }
}
