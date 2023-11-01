package com.example.chirag_project_106_ageclaculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {view->
            printAge(view)
        }
    }

    private fun printAge(view: View) {
        var myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val selectedDate = "$day/${month + 1}/$year"

                val textView1 = findViewById<TextView>(R.id.textView1)
                textView1.text = selectedDate

                val dob = Calendar.getInstance()
                dob.set(year, month, day)

                // Calculate age
                var age = myCalendar.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
                if (myCalendar.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                    age--
                }

                // Calculate total hours and minutes
                val currentTime = Calendar.getInstance()
                val timeDifferenceInMillis = currentTime.timeInMillis - dob.timeInMillis
                val totalHours = (timeDifferenceInMillis / (60 * 60 * 1000)).toInt()
                val totalMinutes = (timeDifferenceInMillis / (60 * 1000)).toInt()

                val textView2 = findViewById<TextView>(R.id.textView2)
                textView2.text = "You are $age years old."

                // Update the newly added TextViews
                val textViewHours = findViewById<TextView>(R.id.textViewHours)
                textViewHours.text = "Total Hours: $totalHours"

                val textViewMinutes = findViewById<TextView>(R.id.textViewMinutes)
                textViewMinutes.text = "Total Minutes: $totalMinutes"
            },
            year,
            month,
            day
        ).show()
    }

}