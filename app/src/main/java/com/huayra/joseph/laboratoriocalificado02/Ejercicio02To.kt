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
    private val NUMBER_KEY = "NUMBER_KEY"
    private val CITY_KEY = "CITY_KEY"
    private val PRODUCTS_KEY = "PRODUCTS_KEY"

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
        }
    }

    private fun observeButtons(bundle: Bundle?) {
        binding.imbWsp.setOnClickListener { goWsp(bundle) }
        binding.imbLlamar.setOnClickListener { goCall(bundle) }
        binding.imbMaps.setOnClickListener { goMaps(bundle) }
    }

    private fun goWsp(bundle: Bundle?) {
        val phone = bundle?.getString(NUMBER_KEY)
        val name = bundle?.getString(NAME_KEY, "Nombre no disponible")
        val products = bundle?.getString(PRODUCTS_KEY, "Productos no disponibles")
        val location = bundle?.getString(CITY_KEY, "Ubicación no disponible")

        val message = "Hola $name, Tus productos: $products están en camino a la dirección: $location"

        if (phone != null) {
            val encodedMessage = Uri.encode(message)
            Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/+51$phone?text=$encodedMessage")).also {
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
