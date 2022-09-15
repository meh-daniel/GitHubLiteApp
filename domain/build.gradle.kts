plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}
repositories {
    mavenCentral()
    google()
}
java {
    sourceCompatibility = Options.compileOptions
    targetCompatibility = Options.compileOptions
}

dependencies {

    // Javax Inject
    implementation(Dependencies.Javax.inject)

    // Kotlin
    implementation(Dependencies.Coroutines.core)

    // Paging
    implementation(Dependencies.Paging.common)
}