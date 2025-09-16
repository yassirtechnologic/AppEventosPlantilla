package com.appeventos.plantilla

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import android.graphics.drawable.ColorDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.io.File

class ImagenCompletaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentInBase(R.layout.activity_imagen_completa)
        mostrarBotonVolver(true)

        val img = findViewById<ImageView>(R.id.imgCompleta)

        val resId = intent.getIntExtra(EXTRA_RES_ID, 0)
        val path  = intent.getStringExtra(EXTRA_PATH)
        val url   = intent.getStringExtra(EXTRA_URL)

        if (!loadInto(img, resId, path, url)) {
            finish()
        }
    }

    private fun loadInto(img: ImageView, resId: Int, path: String?, url: String?): Boolean {
        val placeholder = ColorDrawable(
            ContextCompat.getColor(this, android.R.color.darker_gray)
        )

        return when {
            resId != 0 -> {
                Glide.with(this)
                    .load(resId)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(placeholder)
                    .error(placeholder)
                    .fitCenter()
                    .into(img)
                true
            }
            !path.isNullOrBlank() -> {
                Glide.with(this)
                    .load(File(path))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(placeholder)
                    .error(placeholder)
                    .fitCenter()
                    .into(img)
                true
            }
            !url.isNullOrBlank() -> {
                Glide.with(this)
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(placeholder)
                    .error(placeholder)
                    .fitCenter()
                    .into(img)
                true
            }
            else -> false
        }
    }

    companion object {
        private const val EXTRA_RES_ID = "EXTRA_RES_ID"
        private const val EXTRA_PATH   = "EXTRA_PATH"
        private const val EXTRA_URL    = "EXTRA_URL"

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








