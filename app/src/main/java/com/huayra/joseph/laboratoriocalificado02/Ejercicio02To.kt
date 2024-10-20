package com.huayra.joseph.laboratoriocalificado02

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.huayra.joseph.laboratoriocalificado02.databinding.Ejercicio02toBinding

class Ejercicio02To : AppCompatActivity() {

    private lateinit var binding: Ejercicio02toBinding

    private val NAME_KEY = "NAME_KEY"
    private val NUMBER_KEY = "NUMBER_KEY" // Cambié este nombre para que coincida con Ejercicio02
    private val CITY_KEY = "CITY_KEY" // Cambié este nombre para que coincida con Ejercicio02
    private val PRODUCTS_KEY = "PRODUCTS_KEY" // Cambié este nombre para que coincida con Ejercicio02

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Ejercicio02toBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showInformation(intent.extras)
        observeButtons(intent.extras)
    }

    private fun showInformation(bundle: Bundle?) {
        bundle?.run {
            val name = getString(NAME_KEY, "Nombre no disponible")
            val phone = getString(NUMBER_KEY, "Teléfono no disponible")
            val location = getString(CITY_KEY, "Ubicación no disponible")
            val products = getString(PRODUCTS_KEY, "Productos no disponibles")

            binding.tvName.text = "Nombre completo: $name"
            binding.tvPhone.text = "Número telefónico: $phone"
            binding.tvLocation.text = "Ubicación: $location"
            binding.tvProduct.text = "Productos: $products"
        } ?: run {
            // Puedes mostrar un mensaje de error aquí si no hay datos
        }
    }

    private fun observeButtons(bundle: Bundle?) {
        binding.imbWsp.setOnClickListener { goWsp(bundle) }
        binding.imbLlamar.setOnClickListener { goCall(bundle) }
        binding.imbMaps.setOnClickListener { goMaps(bundle) }
    }

    private fun goWsp(bundle: Bundle?) {
        val phone = bundle?.getString(NUMBER_KEY)
        val message = "Hola, te he agregado a mi lista de contactos"

        if (phone != null) {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/+51$phone?text=$message")).also {
                startActivity(it)
            }
        } else {
        }
    }

    private fun goCall(bundle: Bundle?) {
        val phone = bundle?.getString(NUMBER_KEY)
        if (phone != null) {
            Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")).also {
                startActivity(it)
            }
        } else {
        }
    }

    private fun goMaps(bundle: Bundle?) {
        val location = bundle?.getString(CITY_KEY)
        if (location != null) {
            Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$location")).also {
                startActivity(it)
            }
        } else {
        }
    }
}
