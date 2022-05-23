
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class JavaLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.plugins.apply("java-library")
        project.plugins.apply("kotlin-kapt")
        project.plugins.apply("org.jlleitschuh.gradle.ktlint")

        project.dependencies {

            add("implementation", Coroutines.core)

            add("implementation", Retrofit.core)
            add("implementation", Retrofit.gson)
            add("implementation", AndroidX.okHttp_logger)

            add("implementation", Koin.core)

            add("implementation", project(Modules.common))
        }
    }
}
