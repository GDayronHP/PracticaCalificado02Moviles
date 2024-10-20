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
    private val PHONE_KEY = "PHONE_KEY"
    private val LOCATION_KEY = "LOCATION_KEY"
    private val PRODUCT_KEY = "PRODUCT_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Ejercicio02toBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showInformation(intent.extras)
        observeButtons(intent.extras)
    }

    private fun showInformation(bundle: Bundle?) {
        bundle?.let {
            val name = it.getString(NAME_KEY, "Nombre no disponible")
            val phone = it.getString(PHONE_KEY, "Teléfono no disponible")
            val location = it.getString(LOCATION_KEY, "Ubicación no disponible")
            val products = it.getString(PRODUCT_KEY, "Productos no disponibles")

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
        val phone = "+51${bundle?.getString(PHONE_KEY)}"
        val message = "Hola, te he agregado a mi lista de contactos"

        Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$phone?text=$message")).also {
            startActivity(it)
        }
    }

    private fun goCall(bundle: Bundle?) {
        val phone = bundle?.getString(PHONE_KEY)
        Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")).also {
            startActivity(it)
        }
    }

    private fun goMaps(bundle: Bundle?) {
        val location = bundle?.getString(LOCATION_KEY)
        Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$location")).also {
            startActivity(it)
        }
    }
}
