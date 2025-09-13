package com.appeventos.plantilla

import android.os.Bundle

class VeganosCeliacosActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout dentro del contenedor del layout base
        setContentInBase(R.layout.activity_veganos_celiacos)

        // Muestra el botón de volver
        mostrarBotonVolver(true)
    }
}



