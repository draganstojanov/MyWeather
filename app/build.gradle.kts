plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}


android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.andraganoid.myweather"
        minSdk = 23
        targetSdk = 33
        versionCode = 3
        versionName = "1.2"


//        kapt {
//            correctErrorTypes true
//            arguments {
//                arg("room.schemaLocation", "$projectDir/schemas")
//            }
//        }
    }


    buildTypes {
        getByName("debug") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            isMinifyEnabled = false
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            isDebuggable = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }


    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

}

dependencies {

    val hiltVersion= "2.43.2"
    val lifecycleVersion = "2.5.1"
    val roomVersion = "2.4.3"
    val retrofitVersion = "2.9.0"

    // lib
    implementation(project(":connectivity"))
    implementation(project(":prefs"))

    // AndroidX
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.fragment:fragment-ktx:1.5.2")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    //Dagger hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // Coil
    implementation("io.coil-kt:coil:2.2.0")

    // Desugar
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    //Google
    implementation("com.google.android.gms:play-services-location:20.0.0")

    // ViewBinding Delegate
    implementation("com.github.yogacp:android-viewbinding:1.0.3")

    //Chucker
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")

}







