package com.appeventos.plantilla

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appeventos.plantilla.utils.AdminPrefs
import java.io.File
import java.io.FileOutputStream

class GaleriaActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var galeriaAdapter: GaleriaAdapter
    private lateinit var imagenes: MutableList<File>
    private val galeriaDir: File by lazy { File(getExternalFilesDir(null), "galeria") }

    private var btnSubir: Button? = null
    private var tvSoloAdmin: TextView? = null
    private var tvVacio: TextView? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) guardarImagen(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout en el contenedor de BaseActivity
        setContentInBase(R.layout.activity_galeria)
        mostrarBotonVolver(true)

        btnSubir = findViewById(R.id.btnSubirImagen)
        tvSoloAdmin = findViewById(R.id.tvSoloAdmin)
        tvVacio = findViewById(R.id.tvVacio)
        recyclerView = findViewById(R.id.recyclerGaleria)

        val esAdmin = AdminPrefs.isAdmin(this)

        if (!galeriaDir.exists()) galeriaDir.mkdirs()

        imagenes = galeriaDir
            .listFiles()
            ?.sortedByDescending { it.lastModified() }
            ?.toMutableList()
            ?: mutableListOf()

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        galeriaAdapter = GaleriaAdapter(
            imagenes,
            esAdmin,
            { file ->
                val i = Intent(this, ImagenCompletaActivity::class.java)
                i.putExtra("EXTRA_PATH", file.absolutePath)
                startActivity(i)
            },
            { file -> confirmarEliminar(file) }
        )
        recyclerView.adapter = galeriaAdapter

        if (esAdmin) {
            btnSubir?.visibility = View.VISIBLE
            tvSoloAdmin?.visibility = View.GONE
            btnSubir?.setOnClickListener { pickImage.launch("image/*") }
        } else {
            btnSubir?.visibility = View.GONE
            tvSoloAdmin?.visibility = View.VISIBLE
        }

        actualizarEstadoVacio()
    }

    private fun guardarImagen(uri: Uri) {
        val nombre = obtenerNombreArchivo(uri)
        val destino = File(galeriaDir, nombre)

        if (destino.exists()) {
            toast("Esa imagen ya existe")
            return
        }

        try {
            contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(destino).use { output -> input.copyTo(output) }
            }
            imagenes.add(0, destino)
            galeriaAdapter.notifyItemInserted(0)
            recyclerView.scrollToPosition(0)
            actualizarEstadoVacio()
        } catch (e: Exception) {
            toast("Error al guardar la imagen")
        }
    }

    private fun actualizarEstadoVacio() {
        tvVacio?.visibility = if (imagenes.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun obtenerNombreArchivo(uri: Uri): String {
        var nombre = "imagen_${System.currentTimeMillis()}.jpg"
        contentResolver.query(uri, null, null, null, null)?.use { c ->
            if (c.moveToFirst()) {
                val idx = c.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (idx >= 0) nombre = c.getString(idx)
            }
        }
        return nombre
    }

    private fun confirmarEliminar(file: File) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar imagen")
            .setMessage("¿Deseas eliminar esta imagen?")
            .setPositiveButton("Sí") { _, _ ->
                val index = imagenes.indexOf(file)
                if (index != -1 && file.delete()) {
                    imagenes.removeAt(index)
                    galeriaAdapter.notifyItemRemoved(index)
                    actualizarEstadoVacio()
                } else {
                    toast("No se pudo eliminar")
                }
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}









