import org.gradle.api.JavaVersion

object Versions {
    const val kotlin_version = "1.6.10"
    const val buildToolsVersion = "30.0.2"
    const val gradleBuildTools = "7.2.0"

    const val compileSDK = 32
    const val minSDK = 24
    const val targetSDK = 32

    const val applicationId = "sk.globing.notesappandroid"
    const val versionCode = 1
    const val versionName = "0.1"

    val sourceCompatibilityJava = JavaVersion.VERSION_11
    val targetCompatibilityJava = JavaVersion.VERSION_11

    val jwmTarget = JavaVersion.VERSION_11.toString()
}

object BuildPlugins {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.gradleBuildTools}"
    const val androidApplication = "com.android.application"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
}

object AndroidX {
    const val core = "androidx.core:core-ktx:1.6.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
    const val material = "com.google.android.material:material:1.4.0"
    const val security = "androidx.security:security-crypto:1.0.0"
    const val preference = "androidx.preference:preference:1.1.1"
    const val okHttp_logger = "com.squareup.okhttp3:logging-interceptor:4.9.2"
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"

    object Lifecycle {
        private const val version = "2.4.1"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$version"
        const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
        const val viewModelComposable = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
    }

    object Navigation {
        private const val version = "2.4.2"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ui = "androidx.navigation:navigation-ui-ktx:$version"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
    }
    object Compose {
        const val composeVersion = "1.1.1"

        const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
        const val ui = "androidx.compose.ui:ui:$composeVersion"
        const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout:$composeVersion"
        const val material = "androidx.compose.material:material:$composeVersion"
        const val materialIconsCompose = "androidx.compose.material:material-icons-extended:$composeVersion"
        const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:$composeVersion"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"

    }
}

object Coroutines {
    private const val version = "1.6.0"
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
}

object Koin {
    private const val version = "3.1.0"
    const val android = "io.insert-koin:koin-android:$version"
    const val core = "io.insert-koin:koin-core:$version"
}

object Retrofit {
    private const val version = "2.9.0"
    const val core = "com.squareup.retrofit2:retrofit:$version"
    const val gson = "com.squareup.retrofit2:converter-gson:$version"
}
object Kotlin {
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val kotlinDate = "org.jetbrains.kotlinx:kotlinx-datetime:0.3.1"
}

object Room {
    const val version = "2.4.2"
    const val roomKtx = "androidx.room:room-ktx:$version"
    const val roomCompiler = "androidx.room:room-compiler:$version"
}

object Logging {
    const val timber = "com.jakewharton.timber:timber:5.0.1"
}

object TestLibs {
    const val junit = "junit:junit:4.13.2"
    const val mockkUnit = "io.mockk:mockk:1.12.0"
}
