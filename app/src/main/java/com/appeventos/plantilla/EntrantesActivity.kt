package com.appeventos.plantilla

import android.os.Bundle

class EntrantesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el contenido específico dentro del layout base
        setContentInBase(R.layout.activity_entrantes)

        // Mostrar botón volver
        mostrarBotonVolver(true)
    }
}


