package com.appeventos.plantilla

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // ✅ layout correcto

        val btnAbrirMenu = findViewById<Button>(R.id.btnAbrirMenu)
        btnAbrirMenu.setOnClickListener {
            startActivity(Intent(this, MenuPrincipalActivity::class.java))
            // Si no quieres volver a MainActivity cuando el usuario pulse "atrás":
            // finish()
        }
    }
}









