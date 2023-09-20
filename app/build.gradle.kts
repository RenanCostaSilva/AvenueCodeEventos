import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "br.com.renancsdev.avenuecodeeventos"
    compileSdk = 33

    defaultConfig {
        applicationId = "br.com.renancsdev.avenuecodeeventos"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    dataBinding{
        enable = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    val livedataVersion = "2.2.0"
    val retrofit2Version = "2.9.0"
    val gsonVersion = "2.10.1"
    val glideVersion = "4.16.0"
    val picassoVersion = "2.71828"
    val multidexVersion = "2.0.1"

    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-extensions:$livedataVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit2Version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit2Version")

    // Gson
    implementation("com.google.code.gson:gson:$gsonVersion")

    // Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    //Imagem
    implementation("com.squareup.picasso:picasso:$picassoVersion")


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}