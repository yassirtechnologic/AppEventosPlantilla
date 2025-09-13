package com.appeventos.plantilla

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FormularioActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el formulario dentro del contenedor de BaseActivity
        setContentInBase(R.layout.formulario_evento)
        mostrarBotonVolver(true)

        // Campos
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etFecha = findViewById<EditText>(R.id.etFecha)
        val etUbicacion = findViewById<EditText>(R.id.etUbicacion)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        val storage = EventoStorage(this)

        // Guardar evento
        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val fecha = etFecha.text.toString().trim()
            val ubicacion = etUbicacion.text.toString().trim()
            val descripcion = etDescripcion.text.toString().trim()

            if (nombre.isEmpty() || fecha.isEmpty() || ubicacion.isEmpty() || descripcion.isEmpty()) {
                toast("Por favor, completa todos los campos")
                return@setOnClickListener
            }

            val evento = Evento(nombre, fecha, ubicacion, descripcion)
            storage.guardarEvento(evento)

            toast("✅ Evento guardado con éxito")
            finish()
        }
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}




