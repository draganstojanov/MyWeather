plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}


android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.andraganoid.myweather"
        minSdk = 23
        targetSdk = 34
        versionCode = 5
        versionName = "1.4"
    }

    buildTypes {
        getByName("debug") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            isMinifyEnabled = false
            isDebuggable = true
        }
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            isMinifyEnabled = true
            isDebuggable = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }


    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    namespace = "com.andraganoid.myweather"

}

dependencies {

    val hiltVersion = "2.48.1"
    val lifecycleVersion = "2.6.2"
    val roomVersion = "2.6.0"
    val retrofitVersion = "2.9.0"
    val chuckerVersion = "4.0.0"

    // lib
    implementation(project(":connectivity"))
    implementation(project(":prefs"))

    // AndroidX
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    //Dagger hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    // Coil
    implementation("io.coil-kt:coil:2.5.0")

    // Desugar
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    // kapt("androidx.room:room-compiler:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    //Google
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // ViewBinding Delegate
    implementation("com.github.yogacp:android-viewbinding:1.0.3")

    //Chucker
    debugImplementation("com.github.chuckerteam.chucker:library:$chuckerVersion")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:$chuckerVersion")

}







