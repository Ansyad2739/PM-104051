package com.example.lab02_kalkulator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentInput = ""
    private var firstValue = 0.0
    private var currentOperator = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        // Jawnie zdefiniowana lista przycisków numerycznych
        val numberButtons = listOf(
            Pair(R.id.btn0, "0"),
            Pair(R.id.btn1, "1"),
            Pair(R.id.btn2, "2"),
            Pair(R.id.btn3, "3"),
            Pair(R.id.btn4, "4"),
            Pair(R.id.btn5, "5"),
            Pair(R.id.btn6, "6"),
            Pair(R.id.btn7, "7"),
            Pair(R.id.btn8, "8"),
            Pair(R.id.btn9, "9")
        )

        for ((id, digit) in numberButtons) {
            findViewById<Button>(id).setOnClickListener {
                if (isNewOperation) {
                    currentInput = ""
                    isNewOperation = false
                }
                currentInput += digit
                tvResult.text = currentInput
            }
        }

        // Przycisk kasowania poprzedniego znaka (K)
        findViewById<Button>(R.id.btnKasowanie).setOnClickListener {
            if (currentInput.isNotEmpty() && !isNewOperation) {
                currentInput = currentInput.dropLast(1)
                if (currentInput.isEmpty() || currentInput == "-") {
                    currentInput = ""
                    tvResult.text = "0"
                } else {
                    tvResult.text = currentInput
                }
            }
        }


        // Przycisk czyszczenia (C)
        findViewById<Button>(R.id.btnC).setOnClickListener {
            currentInput = ""
            firstValue = 0.0
            currentOperator = ""
            tvResult.text = "0"
            isNewOperation = true
        }

        // Przycisk zmiany znaka (+/-)
        findViewById<Button>(R.id.btnZmiana).setOnClickListener {
            val zmiana = currentInput.toDoubleOrNull()
            if (zmiana !=null) {
                val newZmiana = -zmiana
                currentInput = if (newZmiana % 1.0 == 0.0) {
                    newZmiana.toInt().toString()
                } else {
                    newZmiana.toString()
                }
                tvResult.text = currentInput
                isNewOperation = false
            }
        }

        // Przycisk kropki (.)
        findViewById<Button>(R.id.btnKropka).setOnClickListener {
            if (isNewOperation) {
                currentInput = "0"
                isNewOperation = false
            } else if (currentInput.isEmpty()) {
                currentInput = "0"
            }
            if (!currentInput.contains(".")) {
                currentInput += "."
                tvResult.text = currentInput
            }
        }

        // Przycisk procentu (%)
        findViewById<Button>(R.id.btnProcent).setOnClickListener {
            if (currentInput.isNotEmpty()) {
                val value = currentInput.toDouble() / 100
                currentInput = if (value % 1.0 == 0.0) value.toInt().toString() else value.toString()
                tvResult.text = currentInput
                isNewOperation = true
            }
        }

        // Operatory arytmetyczne (+, *, /, -)
        val operatorButtons = listOf(
            Pair(R.id.btnPlus, "+"),
            Pair(R.id.btnMultiply, "*"),
            Pair(R.id.btnDivide, "/"),
            Pair(R.id.btnMinus, "-")
        )

        for ((id, op) in operatorButtons) {
            findViewById<Button>(id).setOnClickListener {
                if (currentInput.isNotEmpty()) {
                    firstValue = currentInput.toDouble()
                    currentOperator = op
                    isNewOperation = true
                }
            }
        }

        // Przycisk równa się (=)
        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            if (currentInput.isNotEmpty() && currentOperator.isNotEmpty()) {
                val secondValue = currentInput.toDouble()
                val result = when (currentOperator) {
                    "+" -> firstValue + secondValue
                    "*" -> firstValue * secondValue
                    "/" -> if (secondValue != 0.0) firstValue / secondValue else 0.0
                    "-" -> firstValue - secondValue
                    else -> 0.0
                }
                tvResult.text = if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()
                currentInput = tvResult.text.toString()
                currentOperator = ""
                isNewOperation = true
            }
        }
    }
}