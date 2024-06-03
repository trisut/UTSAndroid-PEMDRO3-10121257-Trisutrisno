package com.example.utsandroid_pemdro3_10121257_trisutrisno

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.app.DatePickerDialog
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


//10121257 TRI SUTRISNO
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextDateConfirmed: EditText = findViewById(R.id.editTextDateConfirmed)
        val editTextDOB: EditText = findViewById(R.id.editTextDOB)
        val editTextNIK: EditText = findViewById(R.id.editTextNIK)
        val editTextName: EditText = findViewById(R.id.editTextName)

        val radioGroupTestType: RadioGroup = findViewById(R.id.radioGroupTestType)
        val radioGroupGender: RadioGroup = findViewById(R.id.radioGroupGender)
        val radioGroupRelationship: RadioGroup = findViewById(R.id.radioGroupRelationship)

        val checkBoxConfirm: CheckBox = findViewById(R.id.checkBoxConfirm)

        val calendar = Calendar.getInstance()

        val dateConfirmedSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val date = "${dayOfMonth}/${monthOfYear + 1}/${year}"
            editTextDateConfirmed.setText(date)
        }

        val dobSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val date = "${dayOfMonth}/${monthOfYear + 1}/${year}"
            editTextDOB.setText(date)
        }

        editTextDateConfirmed.setOnClickListener {
            DatePickerDialog(this, dateConfirmedSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        editTextDOB.setOnClickListener {
            DatePickerDialog(this, dobSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val buttonNext: Button = findViewById(R.id.buttonNext)
        buttonNext.setOnClickListener {
            val selectedTestTypeId = radioGroupTestType.checkedRadioButtonId
            val selectedGenderId = radioGroupGender.checkedRadioButtonId
            val selectedRelationshipId = radioGroupRelationship.checkedRadioButtonId

            if (selectedTestTypeId != -1 && selectedGenderId != -1 && selectedRelationshipId != -1 && checkBoxConfirm.isChecked) {
                val selectedTestType: RadioButton = findViewById(selectedTestTypeId)
                val selectedGender: RadioButton = findViewById(selectedGenderId)
                val selectedRelationship: RadioButton = findViewById(selectedRelationshipId)

                val intent = Intent(this, MainActivity2::class.java).apply {
                    putExtra("TEST_TYPE", selectedTestType.text.toString())
                    putExtra("DATE_CONFIRMED", editTextDateConfirmed.text.toString())
                    putExtra("NIK", editTextNIK.text.toString())
                    putExtra("NAME", editTextName.text.toString())
                    putExtra("DOB", editTextDOB.text.toString())
                    putExtra("GENDER", selectedGender.text.toString())
                    putExtra("RELATIONSHIP", selectedRelationship.text.toString())
                }

                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all required fields and accept the confirmation", Toast.LENGTH_SHORT).show()
            }
        }
    }
}