package com.appeventos.plantilla

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MenuPrincipalActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout base
        setContentInBase(R.layout.activity_menu_principal)
        mostrarBotonVolver(false) // menú principal no necesita volver

        configurarBotonesMenu()
    }

    private fun configurarBotonesMenu() {
        val botones: Map<Int, Class<*>> = mapOf(
            R.id.btnInicio       to InicioActivity::class.java,
            R.id.btnCrear        to FormularioActivity::class.java,
            R.id.btnVer          to ListaEventosActivity::class.java,
            R.id.btnGaleria      to GaleriaActivity::class.java,
            R.id.btnContacto     to ContactoActivity::class.java,
            R.id.btnTestimonios  to TestimoniosActivity::class.java,
            R.id.btnMenuComida   to MenuComidaActivity::class.java
        )

        botones.forEach { (id, activityClass) ->
            findViewById<Button?>(id)?.setOnClickListener {
                startActivity(Intent(this, activityClass))
            }
        }
    }
}










