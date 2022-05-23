import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}
gradlePlugin {
    plugins {
        register("android-library-plugin") {
            id = "android-library-plugin"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("java-library-plugin") {
            id = "java-library-plugin"
            implementationClass = "JavaLibraryPlugin"
        }
    }
}
repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:7.2.0")
    implementation(kotlin("gradle-plugin", "1.6.10"))
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "11"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}
