plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.kotlintest_lib"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kotlintest_lib"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt para inyección de dependencias en Android
    implementation("com.google.dagger:hilt-android:2.48") // Hilt Android
    kapt("com.google.dagger:hilt-compiler:2.48") // Hilt Compiler

// ViewModel y LiveData para manejo de ciclos de vida y datos reactivos
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2") // ViewModel KTX
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2") // LiveData KTX
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2") // Lifecycle Runtime KTX
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2") // ViewModel SavedState

// Retrofit para consumir APIs RESTful
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Converter Gson

// OkHttp para realizar solicitudes HTTP y logging
    implementation("com.squareup.okhttp3:okhttp:4.10.0") // OkHttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0") // Logging Interceptor

// Gson para convertir objetos Java a JSON y viceversa
    implementation("com.google.code.gson:gson:2.10.1") // Gson

// Chucker para depuración de solicitudes HTTP (en modo debug)
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2") // Chucker Debug
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2") // Chucker No-Op en release

// Room para acceso a base de datos local en Android
    implementation("androidx.room:room-runtime:2.5.2") // Room Runtime
    kapt("androidx.room:room-compiler:2.5.2") // Room Compiler
    implementation("androidx.room:room-ktx:2.5.2") // Room KTX

// Navigation para facilitar la navegación entre fragmentos
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5") // Navigation Fragment KTX
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5") // Navigation UI KTX

// Coil para carga de imágenes en Android de manera eficiente
    implementation("io.coil-kt:coil:2.4.0") // Coil Image Loading



    implementation("androidx.appcompat:appcompat:1.7.0")  // Or the latest stable version
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.12.0")  // Or the latest stable version
    implementation("androidx.cardview:cardview:1.0.0")

}