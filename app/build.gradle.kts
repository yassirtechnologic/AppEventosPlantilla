plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.appeventos.plantilla"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.appeventos.plantilla"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Para compatibilidad de vectores en APIs antiguas
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }

    // Activa ViewBinding en todos los layouts
    buildFeatures {
        viewBinding = true
    }

    // Ajustes de compilación (dejamos Java 11 como tienes ahora)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        // desugaring no es necesario con minSdk 24, así que lo omitimos
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    // Opcional: configura packaging para evitar conflictos si añades libs nativas en el futuro
    packaging {
        resources {
            excludes += setOf(
                "META-INF/DEPENDENCIES",
                "META-INF/AL2.0",
                "META-INF/LGPL2.1"
            )
        }
    }
}

dependencies {
    // AndroidX base
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Material 3 (vía catálogo). Asegúrate que libs.material apunte a 1.12.0 o superior
    implementation(libs.material)

    // JSON
    implementation("com.google.code.gson:gson:2.10.1")

    // Imágenes
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

