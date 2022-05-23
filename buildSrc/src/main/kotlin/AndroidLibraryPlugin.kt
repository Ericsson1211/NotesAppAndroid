
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.plugins.apply("com.android.library")
        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-kapt")
        project.plugins.apply("kotlin-parcelize")
        project.plugins.apply("org.jlleitschuh.gradle.ktlint")

        val androidExtension = project.extensions.getByName("android")

        if (androidExtension is BaseExtension) {
            androidExtension.apply {
                compileSdkVersion(Versions.compileSDK)

                defaultConfig {
                    minSdk = Versions.minSDK
                    targetSdk = Versions.targetSDK

                    versionCode = Versions.versionCode
                    versionName = "${project.version}"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                sourceSets {
                    getByName("main").java.srcDirs("src/main/kotlin")
                    getByName("test").java.srcDirs("src/test/kotlin")
                }
                compileOptions {
                    sourceCompatibility = Versions.sourceCompatibilityJava
                    targetCompatibility = Versions.targetCompatibilityJava
                }
            }
        }

        project.dependencies {

            add("implementation", Coroutines.core)

            add("implementation", Retrofit.core)
            add("implementation", Retrofit.gson)
            add("implementation", AndroidX.okHttp_logger)

            add("implementation", Koin.android)

            add("implementation", project(Modules.common))
        }
    }
}
