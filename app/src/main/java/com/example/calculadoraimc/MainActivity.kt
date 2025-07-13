// MainActivity.kt
package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {

    private var isMale: Boolean = false
    private var isFemale: Boolean = true
    private var currentWeight = 63
    private var currentAge = 23
    private var currentHeight = 120

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var textHeight: TextView
    private lateinit var sliderRangeHeight: RangeSlider

    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var textWeight: TextView

    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var textAge: TextView

    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialComponents()
        initListeners()
        initUI()
    }

    private fun initialComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        textHeight = findViewById(R.id.textHeight)
        sliderRangeHeight = findViewById(R.id.sliderRangeHeight)
        // Weight section
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        textWeight = findViewById(R.id.textWeight)
        // Age section
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        textAge = findViewById(R.id.textAge)

        btnCalculate = findViewById(R.id.btnCalculate)

    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        sliderRangeHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##") //df is DecimalFormat instance to format the height
            currentHeight = df.format(value).toInt()
            textHeight.text = "$currentHeight cm"
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
            }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        // Age section
        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculeteIMC()
            navigeteToResultIMCActivity(result)
        }

    }

    private fun navigeteToResultIMCActivity(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculeteIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() / 100.0 * currentHeight.toDouble() / 100.0)
        return df.format(imc).toDouble()

    }

    private fun setWeight() {
        textWeight.text = currentWeight.toString()
    }
    private fun setAge() {
        textAge.text = currentAge.toString()
    }

    private fun changeGender() {
        isMale = !isMale
        isFemale = !isFemale
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMale))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemale))
    }

    private fun getBackgroundColor(isViewSelectedComponent: Boolean): Int {
        val colorReference = if (isViewSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}