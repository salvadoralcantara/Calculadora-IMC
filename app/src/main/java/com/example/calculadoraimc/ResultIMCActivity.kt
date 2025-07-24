package com.example.calculadoraimc

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvIMC: TextView
    private lateinit var btnReCalculate: Button
    private lateinit var ivResult: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val result: Double = intent.extras?.getDouble(MainActivity.IMC_KEY) ?: -1.0
        val status: String? = intent.getStringExtra("IMC_STATUS")
        val gender: String? = intent.getStringExtra("GENDER") // <-- Recibe el género
        initComponents()
        setResultImage(status, gender)
        initUI(result)
        initListeners()
    }

    private fun setResultImage(status: String?, gender: String?) {
        val imageRes = when (gender) {
            "Femenino" -> when (status) {
                "Bajo" -> R.drawable.imc_bajo_f
                "Normal" -> R.drawable.imc_normal_f
                "Alto" -> R.drawable.imc_alto_f
                else -> R.drawable.imc_normal_f
            }
            else -> when (status) {
                "Bajo" -> R.drawable.imc_bajo
                "Normal" -> R.drawable.imc_normal
                "Alto" -> R.drawable.imc_alto
                else -> R.drawable.imc_normal
            }
        }
        ivResult.setImageResource(imageRes)
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.0..18.4 -> {
                tvResult.text = getString(R.string.Underweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.Underweight))
                tvDescription.text = getString(R.string.UnderweightDescription)
            }
            in 18.51..24.99 -> {
                tvResult.text = getString(R.string.Normal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.Normal))
                tvDescription.text = getString(R.string.NormalDescription)
            }
            in 25.00..29.99 -> {
                tvResult.text = getString(R.string.Overweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.Overweight))
                tvDescription.text = getString(R.string.OverweightDescription)
            }
            in 30.00..99.00 -> {
                tvResult.text = getString(R.string.Obesity)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.Obesity))
                tvDescription.text = getString(R.string.ObesityDescription)
            }
            else -> {
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
        ivResult = findViewById(R.id.ivResult)
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}