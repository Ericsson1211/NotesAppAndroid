// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.10")
        classpath("com.android.tools.build:gradle:7.2.0")
    }
}
plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0" apply false
}