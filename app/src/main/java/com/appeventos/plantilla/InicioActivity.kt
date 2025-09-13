package com.appeventos.plantilla

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.appeventos.plantilla.utils.AdminPrefs

class InicioActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el contenido dentro del layout base y muestra el botón volver
        setContentInBase(R.layout.activity_inicio)
        mostrarBotonVolver(true)

        val logo = findViewById<ImageView>(R.id.logoInicio)

        // Tap corto: tip
        logo.setOnClickListener {
            Toast.makeText(this, "Mantén presionado el logo para alternar modo admin", Toast.LENGTH_SHORT).show()
        }

        // Long press: alternar modo admin
        logo.setOnLongClickListener { v ->
            v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)

            val isAdmin = AdminPrefs.isAdmin(this)

            AlertDialog.Builder(this)
                .setTitle(if (isAdmin) "Desactivar modo administrador" else "Activar modo administrador")
                .setMessage(if (isAdmin) "¿Deseas salir del modo administrador?"
                else "¿Deseas entrar al modo administrador?")
                .setPositiveButton(if (isAdmin) "Desactivar" else "Activar") { _, _ ->
                    AdminPrefs.setAdmin(this, !isAdmin)
                    Toast.makeText(
                        this,
                        if (!isAdmin) "Modo admin ACTIVADO" else "Modo admin DESACTIVADO",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton("Cancelar", null)
                .show()

            true
        }
    }
}

