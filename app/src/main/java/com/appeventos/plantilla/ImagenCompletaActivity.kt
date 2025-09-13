package com.appeventos.plantilla

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.io.File

class ImagenCompletaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla dentro del layout base
        setContentInBase(R.layout.activity_imagen_completa)
        mostrarBotonVolver(true)

        val img = findViewById<ImageView>(R.id.imgCompleta)

        // Soporta 3 orígenes posibles: drawable, archivo local o URL
        val resId = intent.getIntExtra(EXTRA_RES_ID, 0)
        val path  = intent.getStringExtra(EXTRA_PATH)   // opcional
        val url   = intent.getStringExtra(EXTRA_URL)    // opcional

        val glide = Glide.with(this)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(android.R.color.darker_gray)
            .error(android.R.color.darker_gray)

        when {
            resId != 0 -> {
                glide.load(resId).fitCenter().into(img)
            }
            !path.isNullOrEmpty() -> {
                glide.load(File(path)).fitCenter().into(img)
            }
            !url.isNullOrEmpty() -> {
                glide.load(url).fitCenter().into(img)
            }
            else -> {
                // Sin datos -> cerrar silenciosamente
                finish()
                return
            }
        }
    }

    companion object {
        const val EXTRA_RES_ID = "EXTRA_RES_ID"
        const val EXTRA_PATH   = "EXTRA_PATH"
        const val EXTRA_URL    = "EXTRA_URL"

        fun fromRes(context: Context, resId: Int): Intent =
            Intent(context, ImagenCompletaActivity::class.java).apply {
                putExtra(EXTRA_RES_ID, resId)
            }

        fun fromFile(context: Context, file: File): Intent =
            Intent(context, ImagenCompletaActivity::class.java).apply {
                putExtra(EXTRA_PATH, file.absolutePath)
            }

        fun fromUrl(context: Context, url: String): Intent =
            Intent(context, ImagenCompletaActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
            }
    }
}






