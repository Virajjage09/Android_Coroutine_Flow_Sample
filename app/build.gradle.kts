import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    kotlin("kapt")
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.codervj.cleanarchitecturehiltflowsample"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.codervj.cleanarchitecturehiltflowsample"
        minSdk = 21
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        pickFirst("META-INF/gradle/incremental.annotation.processors")
    }
    buildFeatures {
        viewBinding = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)


    //Hilt dependencies
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.activity.ktx)

    //Network module dependency
    implementation(project(":network"))

    //Android/UI test cases dependencies
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.test.core)
    androidTestImplementation(libs.test.core.ktx)
    androidTestImplementation(libs.espresso.contrib)
    androidTestImplementation(libs.espresso.idling)
    androidTestImplementation(libs.androidx.test.ext.junit.ktx)

    testImplementation(libs.espresso.core)
    testImplementation(libs.espresso.intent)
    testImplementation(libs.androidx.test.ext.junit.ktx)

    testImplementation(libs.espresso.idling)

    androidTestImplementation(libs.android.runner)
    androidTestImplementation (libs.androidx.rules)
    androidTestImplementation( libs.espresso.core)
}