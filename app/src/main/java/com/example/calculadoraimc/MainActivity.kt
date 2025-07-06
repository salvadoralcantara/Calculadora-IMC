// MainActivity.kt
package com.example.calculadoraimc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {

    private var isMale: Boolean = false
    private var isFemale: Boolean = true

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var textHeight: TextView
    private lateinit var sliderRangeHeight: RangeSlider

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
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            textHeight.text = "$result cm"
        }
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
    }
}