rootProject.buildFileName = "build.gradle.kts"
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Notes App Android"

include(
    ":app",
    ":core",
    ":common",
    ":data",
    ":domain",
    ":infrastructure"
)
