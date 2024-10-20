package com.huayra.joseph.laboratoriocalificado02

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.huayra.joseph.laboratoriocalificado02.databinding.Ejercicio02Binding

class Ejercicio02 : AppCompatActivity() {
    private lateinit var binding: Ejercicio02Binding
    private val NAME_KEY = "NAME_KEY"
    private val NUMBER_KEY = "NUMBER_KEY"
    private val PRODUCTS_KEY = "PRODUCTS_KEY"
    private val CITY_KEY = "CITY_KEY"
    private val ADDRESS_KEY = "ADDRESS_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Ejercicio02Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        observeButtons()
    }

    private fun observeButtons() {
        binding.RegisterButton.setOnClickListener {
            goDetailActivity()
        }
    }

    private fun goDetailActivity() {
        val name = binding.edtName.text.toString()
        val number = binding.edtPhone.text.toString()
        val products = binding.edtOffice.text.toString()
        val city = binding.edtCity.text.toString()
        val address = binding.edtAddress.text.toString()

        val intent = Intent(this, Ejercicio02To::class.java).apply {
            putExtra(NAME_KEY, name)
            putExtra(NUMBER_KEY, number)
            putExtra(PRODUCTS_KEY, products)
            putExtra(CITY_KEY, city)
            putExtra(ADDRESS_KEY, address)
        }
        startActivity(intent)
    }
}
