package com.appeventos.plantilla

import android.os.Bundle

class MariscoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout dentro del contenedor del layout base
        setContentInBase(R.layout.activity_marisco)

        // Muestra el botón de volver
        mostrarBotonVolver(true)
    }
}



