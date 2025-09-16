package com.appeventos.plantilla

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaEventosActivity : BaseActivity() {

    private lateinit var rvEventos: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var btnCrearEvento: Button
    private lateinit var storage: EventoStorage

    private var adapter: EventosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentInBase(R.layout.activity_lista_eventos)
        mostrarBotonVolver(true)

        rvEventos = findViewById(R.id.rvEventos)
        tvEmpty = findViewById(R.id.tvEmpty)
        btnCrearEvento = findViewById(R.id.btnCrearEvento)

        storage = EventoStorage(this)

        rvEventos.layoutManager = LinearLayoutManager(this)
        adapter = EventosAdapter { ev: Evento ->
            // TODO: abrir detalle si quieres
            // startActivity(DetalleEventoActivity.from(this, ev))
        }
        rvEventos.adapter = adapter

        btnCrearEvento.setOnClickListener {
            startActivity(Intent(this, FormularioActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        cargarEventos()
    }

    private fun cargarEventos() {
        // 🔁 Cambia aquí según tu API real:
        val eventos: List<Evento> = try {
            storage.getAll()                     // si existe
        } catch (_: Throwable) {
            try { storage.listar() }             // si tu método se llama listar()
            catch (_: Throwable) {
                try { storage.obtenerTodos() }   // otro nombre típico
                catch (_: Throwable) { emptyList() }
            }
        }

        adapter?.updateItems(eventos)

        val empty = eventos.isEmpty()
        tvEmpty.visibility = if (empty) View.VISIBLE else View.GONE
        rvEventos.visibility = if (empty) View.GONE else View.VISIBLE
    }
}





