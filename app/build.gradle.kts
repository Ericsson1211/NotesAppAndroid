plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    //id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = Versions.applicationId
    compileSdk = Versions.compileSDK

    defaultConfig {
        applicationId = Versions.applicationId
        minSdk = Versions.minSDK
        targetSdk = Versions.targetSDK
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Versions.sourceCompatibilityJava
        targetCompatibility = Versions.targetCompatibilityJava
    }
    kotlinOptions {
        jvmTarget = Versions.jwmTarget
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidX.Compose.composeVersion
    }
    packagingOptions {
        resources {
            excludes += setOf("META-INF/LICENSE-notice.md", "META-INF/LICENSE.md")
        }
    }

}

dependencies {
    implementation(project(Modules.common))
    implementation(project(Modules.core))
    implementation(project(Modules.domain))

    implementation(AndroidX.core)
    implementation(AndroidX.Lifecycle.runtime)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.material)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.constraintLayout)

    implementation(AndroidX.Compose.runtime)

    implementation(AndroidX.Navigation.fragment)
    implementation(AndroidX.Navigation.ui)

    implementation(Koin.core)
    implementation(Koin.android)
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.mockkUnit)
}