package com.appeventos.plantilla

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.io.File

/**
 * Adapter de galería:
 *  - Click normal -> ver imagen
 *  - Long click (solo admin) -> eliminar
 *  - Botón eliminar (si existe en el layout) -> visible solo para admin
 */
class GaleriaAdapter(
    private val imagenes: MutableList<File>,
    private val isAdmin: Boolean,
    private val onItemClick: (File) -> Unit,
    private val onDelete: (File) -> Unit
) : RecyclerView.Adapter<GaleriaAdapter.ImagenViewHolder>() {

    class ImagenViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        val imagen: ImageView = root.findViewById(R.id.imageView)
        val btnEliminar: ImageButton? = root.findViewById(R.id.btnEliminar) // opcional
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_imagen, parent, false)
        return ImagenViewHolder(v)
    }

    override fun getItemCount(): Int = imagenes.size

    override fun onBindViewHolder(holder: ImagenViewHolder, position: Int) {
        val file = imagenes[position]

        // Carga con Glide para eficiencia
        Glide.with(holder.root.context)
            .load(file)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(android.R.color.darker_gray)
            .error(android.R.color.darker_gray)
            .into(holder.imagen)

        // Click normal -> abrir imagen completa
        holder.root.setOnClickListener { onItemClick(file) }

        // Long press -> eliminar (solo admin)
        holder.root.setOnLongClickListener {
            if (isAdmin) {
                onDelete(file)
                true
            } else false
        }

        // Botón eliminar visible solo para admin
        holder.btnEliminar?.apply {
            isVisible = isAdmin
            setOnClickListener { onDelete(file) }
        }
    }

    override fun onViewRecycled(holder: ImagenViewHolder) {
        // Libera recursos de Glide al reciclar
        Glide.with(holder.root.context).clear(holder.imagen)
        super.onViewRecycled(holder)
    }

    /* ---------- Helpers para manipular la lista ---------- */

    /** Reemplaza todos los ítems */
    fun setItems(nuevas: List<File>) {
        imagenes.clear()
        imagenes.addAll(nuevas)
        notifyDataSetChanged()
    }

    /** Agrega un ítem al inicio */
    fun addFirst(file: File) {
        imagenes.add(0, file)
        notifyItemInserted(0)
    }

    /** Elimina un archivo */
    fun remove(file: File) {
        val idx = imagenes.indexOf(file)
        if (idx >= 0) {
            imagenes.removeAt(idx)
            notifyItemRemoved(idx)
        }
    }

    /** Elimina por posición */
    fun removeAt(pos: Int) {
        if (pos in imagenes.indices) {
            imagenes.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }
}







