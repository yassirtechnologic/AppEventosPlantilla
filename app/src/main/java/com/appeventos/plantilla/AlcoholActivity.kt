package com.appeventos.plantilla

import android.os.Bundle

class AlcoholActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Carga el contenido específico en el contenedor
        layoutInflater.inflate(R.layout.activity_alcohol, findViewById(R.id.contenedorPrincipal))

        // Mostrar botón de volver
        mostrarBotonVolver(mostrar = true)
    }
}

