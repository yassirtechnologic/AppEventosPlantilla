package com.appeventos.plantilla

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class TestimoniosActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla dentro del layout base y muestra botón volver
        setContentInBase(R.layout.activity_testimonios)
        mostrarBotonVolver(true)

        val layoutTestimonios = findViewById<LinearLayout>(R.id.layoutListaTestimonios)
        val tvEmpty = findViewById<TextView>(R.id.tvEmptyTestimonios)
        val inflater = LayoutInflater.from(this)

        // TODO: Reemplaza por datos reales (de red, JSON local o Room).
        val listaTestimonios = listOf(
            Triple("Juan Pérez",  "08/08/2025", "Excelente servicio, la comida espectacular."),
            Triple("María López", "05/08/2025", "Decoración impecable y atención de primera."),
            Triple("Carlos Gómez","02/08/2025", "Todo salió perfecto, gracias a York y Katy.")
        )

        if (listaTestimonios.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            layoutTestimonios.removeAllViews()
        } else {
            tvEmpty.visibility = View.GONE
            layoutTestimonios.removeAllViews()
            listaTestimonios.forEach { (nombre, fecha, texto) ->
                val card = inflater.inflate(R.layout.item_testimonio, layoutTestimonios, false)
                card.findViewById<TextView>(R.id.tvNombreCliente).text = nombre
                card.findViewById<TextView>(R.id.tvFechaTestimonio).text = fecha
                card.findViewById<TextView>(R.id.tvTextoTestimonio).text = texto
                layoutTestimonios.addView(card)
            }
        }
    }
}



