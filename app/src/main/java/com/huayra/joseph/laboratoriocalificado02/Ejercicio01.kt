package com.huayra.joseph.laboratoriocalificado02

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.huayra.joseph.laboratoriocalificado02.databinding.Ejercicio01Binding

class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: Ejercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Ejercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShow.setOnClickListener {
            binding.boxView.visibility = View.VISIBLE
        }

        binding.btnHide.setOnClickListener {
            binding.boxView.visibility = View.GONE
        }
    }
}