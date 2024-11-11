package com.example.fitnessapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WorkoutPlanActivity : AppCompatActivity() {

    private lateinit var workoutPlanText: TextView
    private lateinit var btnArm: Button
    private lateinit var btnChest: Button
    private lateinit var btnLeg: Button
    private lateinit var btnTricep: Button
    private lateinit var btnBiceps: Button
    private lateinit var btnShoulder: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_plan)

        // Initialize the views
        workoutPlanText = findViewById(R.id.workoutPlanText)
        btnArm = findViewById(R.id.btnArm)
        btnChest = findViewById(R.id.btnChest)
        btnLeg = findViewById(R.id.btnLeg)
        btnTricep = findViewById(R.id.btnTricep)
        btnBiceps = findViewById(R.id.btnBiceps)
        btnShoulder = findViewById(R.id.btnShoulder)

        // Setting listeners for each button
        btnArm.setOnClickListener {
            showWorkoutPlan("Arm Workout", getArmWorkout())
        }

        btnChest.setOnClickListener {
            showWorkoutPlan("Chest Workout", getChestWorkout())
        }

        btnLeg.setOnClickListener {
            showWorkoutPlan("Leg Workout", getLegWorkout())
        }

        btnTricep.setOnClickListener {
            showWorkoutPlan("Triceps Workout", getTricepWorkout())
        }

        btnBiceps.setOnClickListener {
            showWorkoutPlan("Biceps Workout", getBicepsWorkout())
        }

        btnShoulder.setOnClickListener {
            showWorkoutPlan("Shoulder Workout", getShoulderWorkout())
        }
    }

    // Function to display the selected workout plan
    private fun showWorkoutPlan(workoutTitle: String, workoutDetails: String) {
        workoutPlanText.text = "$workoutTitle\n\n$workoutDetails"
    }

    // Exercise details for each body part

    private fun getArmWorkout(): String {
        return """
            1. Bicep Curls - 3 sets of 12 reps
            2. Tricep Dips - 3 sets of 15 reps
            3. Hammer Curls - 3 sets of 12 reps
            4. Overhead Tricep Extensions - 3 sets of 12 reps
        """.trimIndent()
    }

    private fun getChestWorkout(): String {
        return """
            1. Bench Press - 4 sets of 10 reps
            2. Push-ups - 3 sets of 20 reps
            3. Chest Fly - 3 sets of 12 reps
            4. Incline Dumbbell Press - 4 sets of 10 reps
        """.trimIndent()
    }

    private fun getLegWorkout(): String {
        return """
            1. Squats - 4 sets of 12 reps
            2. Lunges - 3 sets of 15 reps per leg
            3. Leg Press - 3 sets of 12 reps
            4. Calf Raises - 4 sets of 20 reps
        """.trimIndent()
    }

    private fun getTricepWorkout(): String {
        return """
            1. Tricep Dips - 3 sets of 15 reps
            2. Overhead Tricep Extension - 3 sets of 12 reps
            3. Tricep Kickbacks - 3 sets of 12 reps
        """.trimIndent()
    }

    private fun getBicepsWorkout(): String {
        return """
            1. Bicep Curls - 3 sets of 12 reps
            2. Hammer Curls - 3 sets of 12 reps
            3. Preacher Curls - 3 sets of 10 reps
        """.trimIndent()
    }

    private fun getShoulderWorkout(): String {
        return """
            1. Shoulder Press - 4 sets of 12 reps
            2. Lateral Raises - 3 sets of 15 reps
            3. Front Raises - 3 sets of 12 reps
            4. Arnold Press - 3 sets of 10 reps
        """.trimIndent()
    }
}
