package com.appeventos.plantilla

import android.os.Bundle

class PastelesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout dentro del contenedor del layout base
        setContentInBase(R.layout.activity_pasteles)

        // Mostrar el botón de volver
        mostrarBotonVolver(true)
    }
}




