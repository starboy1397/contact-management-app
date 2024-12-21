plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.mastercoding.contactmanagementapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mastercoding.contactmanagementapp"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures{
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.lifecycle.livedata) // Add LiveData dependency for Java
    implementation(libs.lifecycle.viewmodel) // Add ViewModel dependency for Java
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}