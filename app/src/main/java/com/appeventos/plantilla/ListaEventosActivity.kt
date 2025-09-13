package com.appeventos.plantilla

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaEventosActivity : BaseActivity() {

    private lateinit var rvEventos: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var storage: EventoStorage
    private var adapter: EventosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla dentro del layout base
        setContentInBase(R.layout.activity_lista_eventos)
        mostrarBotonVolver(true)

        // Views
        rvEventos = findViewById(R.id.rvEventos)
        tvEmpty = findViewById(R.id.tvEmpty)
        val btnCrear = findViewById<Button>(R.id.btnCrearEvento)

        storage = EventoStorage(this)
        rvEventos.layoutManager = LinearLayoutManager(this)

        // Crear evento
        btnCrear.setOnClickListener {
            startActivity(android.content.Intent(this, FormularioActivity::class.java))
        }

        cargarEventos()
    }

    override fun onResume() {
        super.onResume()
        cargarEventos() // refresca lista al volver
    }

    private fun cargarEventos() {
        val eventos = storage.obtenerEventos()

        if (eventos.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            rvEventos.visibility = View.GONE
        } else {
            tvEmpty.visibility = View.GONE
            rvEventos.visibility = View.VISIBLE

            if (adapter == null) {
                adapter = EventosAdapter(eventos.toMutableList())
                rvEventos.adapter = adapter
            } else {
                adapter!!.updateItems(eventos)
            }
        }
    }
}




