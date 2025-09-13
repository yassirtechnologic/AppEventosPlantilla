package com.appeventos.plantilla

import android.os.Bundle

class PescadoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout dentro del contenedor del layout base
        setContentInBase(R.layout.activity_pescado)

        // Muestra el botón volver
        mostrarBotonVolver(true)
    }
}


