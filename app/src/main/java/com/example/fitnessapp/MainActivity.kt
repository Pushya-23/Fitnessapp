package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnDietPlan: Button
    private lateinit var btnWorkoutPlan: Button
    private lateinit var btnStepCounter: Button
    private lateinit var btnSleepTracker: Button
    private lateinit var btnMuscleActivation: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Directly initialize buttons and set listeners
        setupButtons()
    }

    private fun setupButtons() {
        // Initialize buttons and set listeners
        btnDietPlan = findViewById(R.id.btnDietPlan)
        btnWorkoutPlan = findViewById(R.id.btnWorkoutPlan)
        btnStepCounter = findViewById(R.id.btnStepCounter)
        btnSleepTracker = findViewById(R.id.btnSleepTracker)
        btnMuscleActivation = findViewById(R.id.btnMuscleActivation)

        btnDietPlan.setOnClickListener {
            startActivity(Intent(this, DietPlanActivity::class.java))
        }

        btnWorkoutPlan.setOnClickListener {
            startActivity(Intent(this, WorkoutPlanActivity::class.java))
        }

        btnStepCounter.setOnClickListener {
            startActivity(Intent(this, StepCounterActivity::class.java))
        }

        btnSleepTracker.setOnClickListener {
            // Ensure SleepTrackerActivity is imported correctly
            startActivity(Intent(this, SleepTrackingActivity::class.java))
        }

        btnMuscleActivation.setOnClickListener {
            startActivity(Intent(this, MuscleActivationActivity::class.java))
        }
    }
}
