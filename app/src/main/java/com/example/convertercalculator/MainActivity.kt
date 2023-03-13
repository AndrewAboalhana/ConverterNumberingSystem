package com.example.convertercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import java.math.BigInteger
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var numberToConvert: TextView
    private lateinit var textResult: TextView
    private lateinit var buttonBinaryFrom: RadioButton
    private lateinit var buttonDecimalFrom: RadioButton
    private lateinit var buttonOctalFrom: RadioButton
    private lateinit var buttonHexFrom: RadioButton
    private lateinit var buttonBinaryTo: RadioButton
    private lateinit var buttonDecimalTo: RadioButton
    private lateinit var buttonOctalTo: RadioButton
    private lateinit var buttonHexTo: RadioButton
    private lateinit var clearButton: Button
    private lateinit var fButton: Button
    private lateinit var dButton: Button
    private lateinit var cButton: Button
    private lateinit var eButton: Button
    private lateinit var bButton: Button
    private lateinit var aButton: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var button0: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        handleButtonClicks()
    }


    private fun initView() {
        numberToConvert = findViewById(R.id.numberToConvert)
        textResult = findViewById(R.id.textResult)
        buttonBinaryFrom = findViewById(R.id.binaryFrom)
        buttonDecimalFrom = findViewById(R.id.decimalFrom)
        buttonOctalFrom = findViewById(R.id.octalFrom)
        buttonHexFrom = findViewById(R.id.hexadecimalFrom)
        buttonBinaryTo = findViewById(R.id.binaryTo)
        buttonDecimalTo = findViewById(R.id.decimalTo)
        buttonOctalTo = findViewById(R.id.octalTo)
        buttonHexTo = findViewById(R.id.hexadecimalTo)
        clearButton = findViewById(R.id.clearButton)
        fButton = findViewById(R.id.fButton)
        dButton = findViewById(R.id.dButton)
        cButton = findViewById(R.id.cButton)
        eButton = findViewById(R.id.eButton)
        bButton = findViewById(R.id.bButton)
        aButton = findViewById(R.id.aButton)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        button0 = findViewById(R.id.button0)
    }

    private fun handleButtonClicks(){

        clearButton.setOnClickListener { clearInput() }

        buttonDecimalFrom.setOnClickListener {
            clearInput()
            disableButtons(cButton, eButton, bButton, dButton, fButton, aButton)
            enableButtons(button2, button3, button4, button5, button6, button7, button8, button9)
        }

        buttonBinaryFrom.setOnClickListener {
            clearInput()
            disableButtons( cButton,
                eButton,
                bButton,
                dButton,
                fButton,
                aButton,
                button2,
                button3,
                button4,
                button5,
                button6,
                button7,
                button8,
                button9)
            enableButtons(button0, button1)
        }

        buttonOctalFrom.setOnClickListener {
            clearInput()
            disableButtons(cButton, eButton, bButton, dButton, fButton, aButton, button8, button9)
            enableButtons(button0, button1, button2, button3, button4, button5, button6, button7)
        }


        buttonHexFrom.setOnClickListener {
            clearInput()
            enableButtons(
                cButton,
                eButton,
                bButton,
                dButton,
                fButton,
                aButton,
                button0,
                button1,
                button2,
                button3,
                button4,
                button5,
                button6,
                button7,
                button8,
                button9)
        }

        buttonDecimalTo.setOnClickListener {
            val input = numberToConvert.text.toString()
            val conversionFunc = when {
                buttonBinaryFrom.isChecked -> ::convertBinaryToDecimal
                buttonOctalFrom.isChecked -> ::convertOctalToDecimal
                buttonHexFrom.isChecked -> ::convertHexToDecimal
                else -> { str -> str }
            }
            textResult.text = convert(input, conversionFunc)
        }

        buttonBinaryTo.setOnClickListener {
            val input = numberToConvert.text.toString()
            val conversionFunc = when {
                buttonDecimalFrom.isChecked -> ::convertDecimalToBinary
                buttonOctalFrom.isChecked -> ::convertOctalToBinary
                buttonHexFrom.isChecked -> ::convertHexToBinary
                else -> { str -> str }
            }
            textResult.text = convert(input, conversionFunc)
        }

        buttonOctalTo.setOnClickListener {
            val input = numberToConvert.text.toString()
            val conversionFunc = when {
                buttonBinaryFrom.isChecked -> ::convertBinaryToOctal
                buttonDecimalFrom.isChecked -> ::convertDecimalToOctal
                buttonHexFrom.isChecked -> ::convertHexToOctal
                else -> { str -> str }
            }
            textResult.text = convert(input, conversionFunc)
        }

        buttonHexTo.setOnClickListener {
            val input = numberToConvert.text.toString()
            val conversionFunc = when {
                buttonBinaryFrom.isChecked -> ::convertBinaryToHex
                buttonDecimalFrom.isChecked -> ::convertDecimalToHex
                buttonOctalFrom.isChecked -> ::convertOctalToHex
                else -> { str -> str }
            }
            textResult.text = convert(input, conversionFunc)
        }



    }


    private fun convert(input: String, conversionFunc: (String) -> String): String {
        return if (input.isEmpty()) {
            "Please Enter Value"
        } else {
            conversionFunc(input)
        }
    }

    private fun enableButtons(vararg buttons: Button) {
        buttons.forEach { it.isEnabled = true }
    }
    private fun disableButtons(vararg buttons: Button) {
        buttons.forEach { it.isEnabled = false }
    }
    private fun clearInput(){
        numberToConvert.text =""
        textResult.text = ""
    }
    fun onClickNumber(v: View){
        val newDigit = (v as Button).text.toString()
        val oldDigit = numberToConvert.text.toString()
        val newTextNumber = oldDigit + newDigit
        numberToConvert.text = newTextNumber
    }
    private fun convertDecimalToBinary(input: String):String {
        val decimalNumber = BigInteger(input)
        return decimalNumber.toString(2)
    }

    private fun convertDecimalToOctal(input: String):String{
        val decimalNum = input.toBigInteger()
        return decimalNum.toString(8)
    }

    private fun convertDecimalToHex(input: String): String {
        val bigInt = BigInteger(input)
        return bigInt.toString(16).uppercase(Locale.getDefault())
    }

    private fun convertBinaryToDecimal(input :String):String {
        val result = BigInteger(input)
        return result.toString(8)

    }

    private fun convertHexToBinary(input: String):String{
        val bigInteger = input.toBigInteger(16)
        return bigInteger.toString(2)

    }

    private fun convertBinaryToHex(input: String): String {
        val decimal = input.toInt(2)
        return Integer.toHexString(decimal)
    }

    private fun convertHexToDecimal(input: String) :String{
        return BigInteger(input, 16).toString()

    }

    private fun convertHexToOctal(input: String) : String{
        val decimal = BigInteger(input, 16)
        return decimal.toString(8)
    }

    private fun convertBinaryToOctal(input: String): String {
        val decimal = input.toInt(2)
        return decimal.toString(8)
    }

    private fun convertOctalToBinary(input: String):String{
        val decimal = input.toLong(8)
        return decimal.toString(2)
    }

    private fun convertOctalToDecimal(input: String) : String{
        return BigInteger(input,8).toString()

    }

    private fun convertOctalToHex(input: String):String{
        val decimal = BigInteger(input, 8)
        return decimal.toString(16)
    }
}