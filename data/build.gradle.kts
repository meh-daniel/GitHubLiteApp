plugins {
    id(Plugins.AGP.LIBRARY)
    kotlin(Plugins.Kotlin.ANDROID)
    kotlin(Plugins.Kotlin.KAPT)
}
android {
    compileSdk = Config.compileSdk
    defaultConfig {
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        minSdk =  Config.minSDK
        targetSdk = Config.targetSDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = Config.Options.compileOptions
        targetCompatibility = Config.Options.compileOptions
    }
    kotlinOptions {
        jvmTarget = Config.Options.kotlinOptions
    }
    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas",)
            arg("room.incremental", "true")
            arg("room.expandProjection", "true")
        }
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation(project(Modules.Features.DOMAIN))
    implementation(Deps.Coroutines.CORE)
    implementation(Deps.Coroutines.ANDROID)
    implementation(Deps.Javax.INJECT)
    implementation(Deps.Room.KTX)
    implementation(Deps.Room.RUNTIME)
    kapt(Deps.Room.COMPILER)
    implementation(Deps.Network.RETROFIT2)
    implementation(Deps.Network.RETROFIT2_GSON)
    implementation(Deps.Network.LOGGING_INERCEPTOR)
    implementation(Deps.Android.CORE_KTX)
    implementation(Deps.Android.APPCOMPAT)
    testImplementation(Deps.Test.JUNIT)
    androidTestImplementation(Deps.Test.ANDROID_JUNIT)
    androidTestImplementation(Deps.Test.ESPRESSO)
    androidTestImplementation(Deps.Test.MOCKITO_CORE)
    androidTestImplementation(Deps.Test.MOCKITO_KOTLIN)
}