plugins {
    id("android-library-plugin")
}

dependencies {
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(Room.roomKtx)
    annotationProcessor(Room.roomCompiler)
    kapt("androidx.room:room-compiler:${Room.version}")
}
