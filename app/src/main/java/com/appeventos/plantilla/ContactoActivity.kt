package com.appeventos.plantilla

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ContactoActivity : BaseActivity() {

    // 🔧 Cambia esto al correo real cuando definas el admin de la plantilla
    private val EMAIL_DESTINO = "admin@ejemplo.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout de contacto dentro del layout base
        setContentInBase(R.layout.activity_contacto)
        mostrarBotonVolver(true)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etMensaje = findViewById<EditText>(R.id.etMensaje)
        val btnWhatsApp = findViewById<Button>(R.id.btnEnviarWhatsApp)
        val btnEmail = findViewById<Button>(R.id.btnEnviarEmail)

        btnWhatsApp.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val mensaje = etMensaje.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (nombre.isEmpty() || mensaje.isEmpty()) {
                toast("Por favor, completa nombre y mensaje")
                return@setOnClickListener
            }

            val texto = Uri.encode("Hola, soy $nombre. $mensaje" + if (email.isNotEmpty()) "\nEmail: $email" else "")
            val uri = Uri.parse("https://wa.me/?text=$texto")
            val intent = Intent(Intent.ACTION_VIEW, uri)

            try {
                startActivity(intent)
            } catch (_: ActivityNotFoundException) {
                toast("No se encontró una app para abrir WhatsApp")
            }
        }

        btnEmail.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val mensaje = etMensaje.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (nombre.isEmpty() || mensaje.isEmpty()) {
                toast("Por favor, completa nombre y mensaje")
                return@setOnClickListener
            }
            if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                toast("El email no parece válido")
                return@setOnClickListener
            }

            val asunto = "Contacto desde la app ${getString(R.string.app_name)}"
            val cuerpo = buildString {
                appendLine("Nombre: $nombre")
                appendLine("Email: $email")
                appendLine()
                appendLine("Mensaje:")
                append(mensaje)
            }

            // ACTION_SENDTO + mailto: evita que aparezcan apps no-email en el chooser
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(EMAIL_DESTINO))
                putExtra(Intent.EXTRA_SUBJECT, asunto)
                putExtra(Intent.EXTRA_TEXT, cuerpo)
            }

            try {
                startActivity(Intent.createChooser(intent, "Enviar email con…"))
            } catch (_: ActivityNotFoundException) {
                toast("No se encontró una app de correo instalada")
            }
        }
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}







