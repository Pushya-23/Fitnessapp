package com.example.fitnessapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DietPlanActivity : AppCompatActivity() {

    private lateinit var dietPlanText: TextView
    private lateinit var btnDiet1: Button
    private lateinit var btnDiet2: Button
    private lateinit var btnDiet3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_plan)

        dietPlanText = findViewById(R.id.dietPlanText)
        btnDiet1 = findViewById(R.id.btnDiet1)
        btnDiet2 = findViewById(R.id.btnDiet2)
        btnDiet3 = findViewById(R.id.btnDiet3)

        // Setting listeners for each button to show the weekly diet plan
        btnDiet1.setOnClickListener {
            showDietPlan("Weight Gain:", getDietPlan1Weekly())
        }

        btnDiet2.setOnClickListener {
            showDietPlan("Fat Loss:", getDietPlan2Weekly())
        }

        btnDiet3.setOnClickListener {
            showDietPlan("Bulky:", getDietPlan3Weekly())
        }
    }

    // Function to display the selected diet plan
    private fun showDietPlan(dietTitle: String, dietDetails: String) {
        // Update the TextView with the selected diet plan for the week
        dietPlanText.text = "$dietTitle\n\n$dietDetails"
    }

    // Weekly Diet Plan 1
    private fun getDietPlan1Weekly(): String {
        return """
            Monday: Oatmeal, Chicken Salad, Grilled Salmon
            Tuesday: Scrambled Eggs, Grilled Veggies, Quinoa Salad
            Wednesday: Smoothie Bowl, Stir-fried Tofu with Rice
            Thursday: Pancakes, Tuna Salad, Baked Chicken
            Friday: Eggs and Avocado, Veggie Wrap, Grilled Shrimp
            Saturday: Protein Smoothie, Quinoa and Roasted Veggies
            Sunday: Pancakes with Berries, Chicken Stir Fry
        """.trimIndent()
    }

    // Weekly Diet Plan 2
    private fun getDietPlan2Weekly(): String {
        return """
            Monday: Scrambled Eggs, Quinoa and Veggies, Stir-fried Tofu with Rice
            Tuesday: Avocado Toast, Chicken Caesar Salad, Grilled Salmon
            Wednesday: Smoothie, Roasted Sweet Potatoes, Veggie Stir-fry
            Thursday: Egg Muffins, Turkey Wraps, Grilled Chicken
            Friday: Poached Eggs, Veggie Pizza, Baked Cod
            Saturday: Protein Shake, Grilled Veggies, Chicken Breast
            Sunday: French Toast, Veggie Quiche, Baked Salmon
        """.trimIndent()
    }

    // Weekly Diet Plan 3
    private fun getDietPlan3Weekly(): String {
        return """
            Monday: Smoothie Bowl, Grilled Chicken Wrap, Sweet Potato Fries
            Tuesday: Scrambled Eggs, Quinoa Salad, Tofu Stir Fry
            Wednesday: Avocado Toast, Grilled Chicken, Roasted Vegetables
            Thursday: Smoothie, Veggie Tacos, Grilled Shrimp
            Friday: Protein Pancakes, Veggie Burger, Grilled Salmon
            Saturday: Smoothie, Roasted Chicken, Rice and Broccoli
            Sunday: Eggs and Spinach, Roasted Sweet Potatoes, Grilled Fish
        """.trimIndent()
    }
}
