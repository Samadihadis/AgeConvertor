package com.samadihadis.ageconvertor

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var textViewSelectedDate: TextView
    private lateinit var textViewAgeInMinutes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewSelectedDate = findViewById(R.id.textViewSelectedDate)
        textViewAgeInMinutes = findViewById(R.id.textViewAgeInMinutes)

        val buttonSelectDate = findViewById<Button>(R.id.buttonSelectDate)
        val textViewPersonName = findViewById<TextView>(R.id.textViewPersonName)

        textViewPersonName.text = getString(R.string.person_age)

        buttonSelectDate.setOnClickListener {
            onButtonClicked()
        }
    }

    private fun onButtonClicked() {

        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, { veiw, year, month, dayOfMonth ->

            val selectedDateInString = "${year}/${month + 1}/${dayOfMonth}"

            textViewSelectedDate.setText(selectedDateInString)

            val simpleDateFormat = SimpleDateFormat("dd/mm/yyyy")
            val selectedDate: Date = simpleDateFormat.parse(selectedDateInString)
            val selectedDateInMinutes = selectedDate.time / 60000

            val currentDate: Date = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

            val currentDateInMinutes = currentDate.time / 60000

            val ageInMinutes = currentDateInMinutes - selectedDateInMinutes
            textViewAgeInMinutes.setText(ageInMinutes.toString())


        },
            year,
            month,
            day
        )

        datePicker.datePicker.maxDate = Date().time

        datePicker.show()

    }
}