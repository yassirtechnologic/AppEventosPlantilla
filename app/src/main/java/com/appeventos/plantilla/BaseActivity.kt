package com.appeventos.plantilla

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private lateinit var contenedor: ViewGroup
    private lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_base)

        contenedor = findViewById(R.id.contenedorPrincipal)
        btnVolver = findViewById(R.id.btnVolver)

        // Back seguro
        btnVolver.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    /** Infla un layout dentro del contenedor del layout base */
    protected fun setContentInBase(@LayoutRes layoutRes: Int) {
        layoutInflater.inflate(layoutRes, contenedor, /* attachToRoot = */ true)
    }

    /** Muestra/oculta el botón volver */
    protected fun mostrarBotonVolver(mostrar: Boolean) {
        btnVolver.visibility = if (mostrar) View.VISIBLE else View.GONE
    }
}


