plugins {
    id("android-library-plugin")
}

dependencies {
    implementation(project(":app", "default"))
    implementation(project(Modules.common))
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.infrastructure))

    implementation(Room.roomKtx)
}
