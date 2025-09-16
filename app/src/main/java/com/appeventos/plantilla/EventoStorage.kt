package com.appeventos.plantilla

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class EventoStorage(private val context: Context) {

    private val gson = Gson()
    private val file = File(context.filesDir, "eventos.json")

    /** Lee todos los eventos del JSON (si no existe, devuelve lista vacía). */
    fun getAll(): List<Evento> {
        if (!file.exists()) return emptyList()
        val json = runCatching { file.readText() }.getOrElse { return emptyList() }
        val type = object : TypeToken<List<Evento>>() {}.type
        return runCatching { gson.fromJson<List<Evento>>(json, type) ?: emptyList() }
            .getOrElse { emptyList() }
    }

    /** Guarda la lista completa en el JSON. */
    fun saveAll(items: List<Evento>) {
        val json = gson.toJson(items)
        file.writeText(json)
    }

    /** Agrega un evento al inicio de la lista y persiste. */
    fun add(ev: Evento) {
        val list = getAll().toMutableList()
        list.add(0, ev)
        saveAll(list)
    }

    /** Limpia el archivo (opcional). */
    fun clear() {
        if (file.exists()) file.delete()
    }

    // 👇 Alias por compatibilidad con llamadas anteriores
    fun listar(): List<Evento> = getAll()
    fun obtenerTodos(): List<Evento> = getAll()

    // Alias para compatibilidad con código antiguo
    fun guardarEvento(ev: Evento) = add(ev)
}
