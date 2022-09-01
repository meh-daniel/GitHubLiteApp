plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

repositories {
    google()
    mavenCentral()
}

java {
    sourceCompatibility = Options.compileOptions
    targetCompatibility = Options.compileOptions
}

dependencies {

    // Javax Inject
    api(Dependencies.Javax.inject)

    // Kotlin
    api(Dependencies.Coroutines.core)

    // Paging
    implementation(Dependencies.Paging.common)
}