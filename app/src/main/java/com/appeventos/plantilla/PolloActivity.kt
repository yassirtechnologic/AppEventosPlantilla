package com.appeventos.plantilla

import android.os.Bundle

class PolloActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout dentro del contenedor del layout base
        setContentInBase(R.layout.activity_pollo)

        // Muestra el botón de volver
        mostrarBotonVolver(true)
    }
}
