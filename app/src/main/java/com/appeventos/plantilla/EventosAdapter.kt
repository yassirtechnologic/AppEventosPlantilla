package com.appeventos.plantilla

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter para la lista de eventos.
 * Requiere un layout de item: R.layout.item_evento con ids:
 *  - tvNombre, tvFecha, tvUbicacion (y opcional tvDescripcion)
 */
class EventosAdapter(
    private val onClick: ((Evento) -> Unit)? = null
) : ListAdapter<Evento, EventosAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<Evento>() {
        override fun areItemsTheSame(oldItem: Evento, newItem: Evento) =
            oldItem.nombre == newItem.nombre && oldItem.fecha == newItem.fecha

        override fun areContentsTheSame(oldItem: Evento, newItem: Evento) =
            oldItem == newItem
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        val tvUbicacion: TextView = itemView.findViewById(R.id.tvUbicacion)
        val tvDescripcion: TextView? = itemView.findViewById(R.id.tvDescripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evento, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val ev = getItem(position)
        holder.tvNombre.text = ev.nombre
        holder.tvFecha.text = ev.fecha
        holder.tvUbicacion.text = ev.ubicacion
        holder.tvDescripcion?.text = ev.descripcion ?: ""
        holder.itemView.setOnClickListener { onClick?.invoke(ev) }
    }

    /** Compat con tu Activity (puedes seguir llamando updateItems). */
    fun updateItems(items: List<Evento>) {
        submitList(items.toList())
    }
}
