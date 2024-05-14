package com.example.proyecto2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerFrom: Spinner = findViewById(R.id.spinnerFrom)
        val spinnerTo: Spinner = findViewById(R.id.spinnerTo)
        val editTextFrom: EditText = findViewById(R.id.editTextFrom)
        val editTextTo: EditText = findViewById(R.id.editTextTo)
        val btnConvertTo: Button = findViewById(R.id.btnConvertTo)


        val metricUnits = resources.getStringArray(R.array.metrico)
        val imperialUnits = resources.getStringArray(R.array.imperial)

        val metricAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, metricUnits)
        val imperialAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, imperialUnits)

        metricAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        imperialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFrom.adapter = metricAdapter
        spinnerTo.adapter = imperialAdapter

        btnConvertTo.setOnClickListener {
            val fromUnit = spinnerFrom.selectedItemPosition
            val toUnit = spinnerTo.selectedItemPosition
            val value = editTextFrom.text.toString().toDoubleOrNull()

            if (value != null) {
                val convertedValue = convertMetricToImperial(value, fromUnit, toUnit)
                editTextTo.setText(convertedValue.toString())
            } else {
                editTextTo.setText("Error")
            }
        }




            }
        }

    private fun convertMetricToImperial(value: Double, fromUnit: Int, toUnit: Int): Double {
        val metricFactors = arrayOf(100.0, 1.0, 0.001) // cm, m, km
        val imperialFactors = arrayOf(39.3701, 3.28084, 0.000621371) // pulgadas, pies, millas
        val metricValue = value / metricFactors[fromUnit] // Convertir a metros primero
        return metricValue * imperialFactors[toUnit] // Multiplicar por factor  de conversión imperial
    }

    private fun convertImperialToMetric(value: Double, fromUnit: Int, toUnit: Int): Double {
        val metricFactors = arrayOf(100.0, 1.0, 0.001) // cm, m, km
        val imperialFactors = arrayOf(39.3701, 3.28084, 0.000621371) // pulgadas, pies, millas
        val imperialValue = value / imperialFactors[fromUnit] // Convertir a pulgadas primero
        return imperialValue * metricFactors[toUnit] // Multiplicar por factor de conversión métrico
    }


