package com.appeventos.plantilla

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

data class Categoria(val titulo: String, val imgRes: Int, val desc: String)

class MenuComidaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Usamos el patrón de la plantilla
        setContentInBase(R.layout.activity_menu_comida)
        mostrarBotonVolver(true)

        renderCategorias(cargarCategorias())
    }

    private fun cargarCategorias(): List<Categoria> = listOf(
        Categoria(getString(R.string.ensaladas_titulo), R.drawable.ensaladas, getString(R.string.ensaladas_desc)),
        Categoria(getString(R.string.pollo_titulo), R.drawable.pollo, getString(R.string.pollo_desc)),
        Categoria(getString(R.string.pescado_titulo), R.drawable.pescado, getString(R.string.pescado_desc)),
        Categoria(getString(R.string.cerdo_titulo), R.drawable.cerdo, getString(R.string.cerdo_desc)),
        Categoria(getString(R.string.ternera_titulo), R.drawable.ternera, getString(R.string.ternera_desc)),
        Categoria(getString(R.string.marisco_titulo), R.drawable.marisco, getString(R.string.marisco_desc)),
        Categoria(getString(R.string.entrantes_titulo), R.drawable.entrantes, getString(R.string.entrantes_desc)),
        // Estos dos no tienen *_titulo en strings.xml (por ahora). Si los agregas, cambia a getString(...)
        Categoria("Alcohol", R.drawable.alcohol, getString(R.string.alcohol_desc)),
        Categoria("Bebidas", R.drawable.bebidas, getString(R.string.bebidas_desc)),
        Categoria(getString(R.string.pasteles_titulo), R.drawable.pasteles, getString(R.string.pasteles_desc)),
        Categoria(getString(R.string.veganos_celiacos_titulo), R.drawable.vegano_celiacos, getString(R.string.veganos_celiacos_desc))
    )

    private fun renderCategorias(items: List<Categoria>) {
        val contenedor = findViewById<LinearLayout>(R.id.layoutListaPlatos)
        val inflater = LayoutInflater.from(this)

        items.forEach { cat ->
            val card = inflater.inflate(R.layout.item_plato, contenedor, false)
            card.findViewById<ImageView>(R.id.imgPlato).setImageResource(cat.imgRes)
            card.findViewById<TextView>(R.id.tvNombrePlato).text = cat.titulo
            card.findViewById<TextView>(R.id.tvDescripcionPlato).text = cat.desc

            card.setOnClickListener { abrirDetalle(cat.titulo) }
            contenedor.addView(card)
        }
    }

    private fun abrirDetalle(titulo: String) {
        val key = titulo.lowercase()

        val intent = when (key) {
            getString(R.string.ensaladas_titulo).lowercase()        -> Intent(this, EnsaladasActivity::class.java)
            getString(R.string.pollo_titulo).lowercase()            -> Intent(this, PolloActivity::class.java)
            getString(R.string.pescado_titulo).lowercase()          -> Intent(this, PescadoActivity::class.java)
            getString(R.string.cerdo_titulo).lowercase()            -> Intent(this, CerdoActivity::class.java)
            getString(R.string.ternera_titulo).lowercase()          -> Intent(this, TerneraActivity::class.java)
            getString(R.string.marisco_titulo).lowercase()          -> Intent(this, MariscoActivity::class.java)
            getString(R.string.entrantes_titulo).lowercase()        -> Intent(this, EntrantesActivity::class.java)
            "alcohol"                                               -> Intent(this, AlcoholActivity::class.java)
            "bebidas"                                               -> Intent(this, BebidasActivity::class.java)
            getString(R.string.pasteles_titulo).lowercase()         -> Intent(this, PastelesActivity::class.java)
            getString(R.string.veganos_celiacos_titulo).lowercase() -> Intent(this, VeganosCeliacosActivity::class.java)
            else -> null
        }

        intent?.let { startActivity(it) }
    }
}




