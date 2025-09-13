package com.appeventos.plantilla

import android.os.Bundle

class BebidasActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el contenido específico dentro del layout base
        setContentInBase(R.layout.activity_bebidas)

        // Mostrar botón volver
        mostrarBotonVolver(true)
    }
}

