package com.appeventos.plantilla

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException

/**
 * Maneja el guardado y recuperación de eventos en almacenamiento interno usando JSON.
 */
class EventoStorage(private val context: Context) {

    private val fileName = "eventos.json"
    private val gson = Gson()

    /**
     * Guarda un nuevo evento en el archivo.
     */
    fun guardarEvento(evento: Evento) {
        val eventos = obtenerEventos().toMutableList()
        eventos.add(evento)
        guardarEventos(eventos)
    }

    /**
     * Devuelve todos los eventos guardados.
     */
    fun obtenerEventos(): List<Evento> {
        return try {
            val file = File(context.filesDir, fileName)
            if (!file.exists()) return emptyList()

            val json = file.readText()
            if (json.isBlank()) return emptyList()

            val type = object : TypeToken<List<Evento>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Reemplaza la lista de eventos completa en el archivo.
     */
    fun guardarEventos(eventos: List<Evento>) {
        try {
            val json = gson.toJson(eventos)
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(json.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * Elimina todos los eventos.
     */
    fun limpiarEventos() {
        try {
            context.deleteFile(fileName)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

