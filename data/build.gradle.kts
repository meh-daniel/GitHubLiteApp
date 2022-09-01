plugins {
    id(Plugins.AGP.library)
    kotlin(Plugins.Kotlin.android)
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk =  Config.minSDK
        targetSdk = Config.targetSDK
    }
    buildTypes {
        getByName(Config.release) {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://example.com/\"")
        }

        getByName(Config.debug) {
            buildConfigField("String", "BASE_URL", "\"https://example.com.debug/\"")
        }
    }
    compileOptions {
        sourceCompatibility = Options.compileOptions
        targetCompatibility = Options.compileOptions
    }
    kotlinOptions {
        jvmTarget = Options.kotlinOptions
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain"))

    // Network
    implementation(Dependencies.Network.retrofit2)
    implementation(Dependencies.Network.retrofit2Gson)
    implementation(Dependencies.Network.logging)
    // Android
    implementation(Dependencies.Android.coreKtx)
}