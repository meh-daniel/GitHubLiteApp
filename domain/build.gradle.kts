plugins {
    id(Plugins.JAVA)
    id(Plugins.JVM)
}
java {
    sourceCompatibility = Config.Options.compileOptions
    targetCompatibility = Config.Options.compileOptions
}
dependencies {
    implementation(Deps.Coroutines.CORE)
    implementation(Deps.Javax.INJECT)
}