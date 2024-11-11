package com.example.fitnessapp

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class StepCounterActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    private lateinit var stepCountText: TextView
    private var stepCount = 0
    private var initialStepCount = 0
    private lateinit var sharedPreferences: SharedPreferences

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val todayDate = dateFormat.format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_counter)

        // Initialize SharedPreferences to store the step count and date
        sharedPreferences = getSharedPreferences("StepCounterPrefs", MODE_PRIVATE)

        // Initialize step count TextView
        stepCountText = findViewById(R.id.stepCountText)

        // Initialize sensor manager and get the step counter sensor
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor == null) {
            Toast.makeText(this, "Step Counter sensor not available!", Toast.LENGTH_LONG).show()
        } else {
            // Load stored data from SharedPreferences
            val lastDate = sharedPreferences.getString("lastDate", "")
            val storedStepCount = sharedPreferences.getInt("dailyStepCount", 0)

            // If the stored date is different from today, reset the step count
            if (lastDate != todayDate) {
                stepCount = 0
                initialStepCount = 0
                sharedPreferences.edit().putString("lastDate", todayDate).apply()  // Update the date
            } else {
                // If it's the same day, use the stored step count
                initialStepCount = storedStepCount
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
            Log.d("StepCounter", "Sensor registered")
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)

        // Save the current step count and the date to SharedPreferences
        with(sharedPreferences.edit()) {
            putInt("dailyStepCount", stepCount)
            putString("lastDate", todayDate)
            apply()
        }
        Log.d("StepCounter", "Sensor unregistered and step count saved")
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            val currentStepCount = event.values[0].toInt()

            // Log current step count for debugging
            Log.d("StepCounter", "Current step count: $currentStepCount")

            // If it's the first time reading the sensor, set the initial step count
            if (initialStepCount == 0) {
                initialStepCount = currentStepCount
                Log.d("StepCounter", "Initial step count set to: $initialStepCount")
            }

            // Calculate the steps since the app was last launched or after reset
            stepCount = currentStepCount - initialStepCount

            // Ensure stepCount is never negative
            if (stepCount < 0) {
                stepCount = 0
            }

            // Update the UI with the calculated steps
            stepCountText.text = "Steps: $stepCount"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not used in this case
    }
}
