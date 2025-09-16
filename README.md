# 📱 App de Eventos – Plantilla Android

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?logo=kotlin&logoColor=white)](#)
[![Android](https://img.shields.io/badge/Android-SDK%2024--34-3DDC84?logo=android&logoColor=white)](#)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Último release](https://img.shields.io/github/v/release/yassirtechnologic/AppEventosPlantilla?sort=semver)](https://github.com/yassirtechnologic/AppEventosPlantilla/releases)

> Una plantilla moderna lista para usar en proyectos de eventos, restaurantes o negocios de servicios.  
> ✅ Fácil de personalizar – cambia logo, colores y textos en minutos.  
> ✅ Construida con **Material 3** y Android Studio.  
> ✅ Compatible con **modo claro y oscuro**.

---

## 🌐 Idiomas
- 🇪🇸 Español (este archivo)
- 🇬🇧 [Read this in English](#) _(pendiente)_

---

## 📦 Descarga

- **APK demo v1.0.0**: **[Descargar](https://github.com/yassirtechnologic/AppEventosPlantilla/releases/download/v1.0.0/AppEventosPlantilla-v1.0.0-demo.apk)**  
- Todos los releases: **https://github.com/yassirtechnologic/AppEventosPlantilla/releases**

---

## 🧭 Contenido
- [Características](#-características-principales)
- [Capturas](#-capturas-de-pantalla)
- [Personalización rápida](#️-personalización-en-5-minutos)
- [Compila y ejecuta](#-compila-y-ejecuta)
- [Estructura de carpetas](#-estructura-de-carpetas)
- [Qué incluye](#-qué-incluye-la-plantilla)
- [Ideal para](#-ideal-para)
- [Roadmap](#-roadmap)
- [Licencia](#-licencia)

---

## 🚀 Características principales

- 🏠 **Menú principal** con secciones: Inicio, Crear evento, Ver eventos, Galería, Contacto, Testimonios y Carta de platos.  
- 📅 **Gestión de eventos**: formulario sencillo + lista dinámica en `eventos.json`.  
- 🖼 **Galería** con subida de imágenes (selector de contenido).  
- 📞 **Contacto** directo por **Email** y **WhatsApp**.  
- 💬 **Testimonios** personalizables.  
- 🍽 **Carta de platos** lista para restaurantes o catering.  
- 🎨 **Material 3**, tema claro/oscuro, estilos y tipografías.  
- 🧩 Código limpio y fácil de adaptar (Kotlin + XML).

---

## 📸 Capturas de Pantalla

> _Sugerencia:_ deja tus imágenes en `/screenshots` y enlázalas aquí.

- **Menú Principal**  
  `screenshots/01_menu.png`
- **Inicio**  
  `screenshots/02_inicio.png`
- **Crear Evento**  
  `screenshots/03_formulario.png`
- **Lista de Eventos**  
  `screenshots/04_lista_eventos.png`
- **Galería**  
  `screenshots/05_galeria.png`
- **Contacto**  
  `screenshots/06_contacto.png`
- **Testimonios**  
  `screenshots/07_testimonios.png`
- **Carta de Platos**  
  `screenshots/08_carta.png`

---

## 🛠️ Personalización en 5 minutos

### 1) Cambia el nombre y textos
Edita `res/values/strings.xml`:

```xml
<string name="app_name">Eventos MiMarca</string>
<string name="app_tagline">Organiza tus fiestas fácilmente 🎉</string>
2) Cambia colores y estilo
Edita res/values/colors.xml (ejemplo):

xml
Copiar código
<color name="colorPrimary">#00695C</color>
<color name="colorSecondary">#FF9800</color>
3) Reemplaza logo e imágenes
res/drawable/logo.png → tu logo

res/drawable/fondo.png → fondo de la app

4) Cambia el ícono de la app
Android Studio → Image Asset Studio → Launcher Icons.

5) (Opcional) Cambia el applicationId
En app/build.gradle.kts:

kotlin
Copiar código
defaultConfig {
    applicationId = "com.tuempresa.tuapp"
}
Recuerda también cambiar el package si lo quieres coherente.

▶️ Compila y ejecuta
Requisitos

Android Studio Giraffe/Koala o superior

JDK 17

Min SDK 24

Pasos

Abre el proyecto en Android Studio.

Conecta un emulador/dispositivo.

Build ▸ Build APK(s) para generar app-debug.apk
Ruta: app/build/outputs/apk/debug/app-debug.apk.

Nota: La galería usa la carpeta privada de la app (getExternalFilesDir), por lo que no requiere permisos. Si usas GetContent/Photo Picker, no necesitas READ_MEDIA_IMAGES ni READ_EXTERNAL_STORAGE en el manifest.

🗂 Estructura de carpetas
swift
Copiar código
app/
 ├─ src/main/
 │   ├─ java/com/appeventos/plantilla/
 │   │   ├─ BaseActivity.kt
 │   │   ├─ ListaEventosActivity.kt
 │   │   ├─ FormularioActivity.kt
 │   │   ├─ GaleriaActivity.kt
 │   │   ├─ ImagenCompletaActivity.kt
 │   │   ├─ Evento.kt / EventoStorage.kt
 │   │   └─ (Adapters, utils, etc.)
 │   └─ res/
 │       ├─ layout/ (pantallas XML)
 │       ├─ drawable/ (imágenes y shapes)
 │       └─ values/ (colors, strings, styles, themes)
 └─ build.gradle.kts
📦 Qué incluye la plantilla
Código fuente Android (Kotlin)

Layouts XML listos

Paleta de colores y estilos (colors.xml, styles.xml, themes.xml)

Strings centralizados (strings.xml)

Ejemplo de imágenes/logo

APK demo en la sección de Releases

🎯 Ideal para
Organizadores de eventos 🎤

Restaurantes y caterings 🍴

Negocios que quieran una app de contacto + catálogo

Freelancers que buscan un producto base para clientes

🗺 Roadmap
Detalle de evento al tocar un ítem

Filtros/búsqueda en la lista

Exportar/Importar eventos.json (backup)

Soporte completo para Photo Picker (Android 13+) en galería

📄 Licencia
Licencia MIT — libre para uso personal y comercial.
Consulta el archivo LICENSE.

🙌 Soporte
¿Encontraste un bug o quieres sugerir una mejora?
Abre un Issue aquí: https://github.com/yassirtechnologic/AppEventosPlantilla/issues
