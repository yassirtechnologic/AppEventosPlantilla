package com.appeventos.plantilla.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AdminPrefs {
    private static final String PREFS = "ek_prefs";
    private static final String KEY_IS_ADMIN = "is_admin";

    // Lee si el modo admin está activo
    public static boolean isAdmin(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_IS_ADMIN, false);
    }

    // Guarda/actualiza el modo admin (true = activo, false = desactivado)
    public static void setAdmin(Context ctx, boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_IS_ADMIN, value).apply();
    }
}

