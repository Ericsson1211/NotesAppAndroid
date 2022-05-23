plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
}
android {
    compileSdk = Versions.compileSDK

    defaultConfig {
        minSdk = Versions.minSDK
        targetSdk = Versions.targetSDK
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    compileOptions {
        sourceCompatibility = Versions.sourceCompatibilityJava
        targetCompatibility = Versions.targetCompatibilityJava
    }

    kotlinOptions {
        jvmTarget = Versions.jwmTarget
    }

}

dependencies {
    implementation(Retrofit.core)
    implementation(Retrofit.gson)
    implementation(AndroidX.okHttp_logger)

    implementation(Koin.core)
}