plugins {
    id(Plugins.AGP.application)
    id(Plugins.Hilt.plugin)
    kotlin(Plugins.Kotlin.android)
    kotlin(Plugins.Kotlin.kapt)
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk =  Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Options.compileOptions
        targetCompatibility = Options.compileOptions
    }

    kotlinOptions {
        jvmTarget = Options.kotlinOptions

        // без данного параметра viewModel не инициализировалась с помощью фабрики
        freeCompilerArgs = listOf("-Xjvm-default=compatibility")
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

    buildFeatures.viewBinding = true
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))

    // Network
    implementation(Dependencies.Network.retrofit2)
    implementation(Dependencies.Network.retrofit2Gson)
    implementation(Dependencies.Network.logging)

    // Hilt
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)

    // Lifecycle
    implementation(Dependencies.Lifecycle.viewmodel)
    implementation(Dependencies.Lifecycle.livedata)
    implementation(Dependencies.Lifecycle.runtime)

    // Navigation
    implementation(Dependencies.Navigation.fragment)
    implementation(Dependencies.Navigation.ui)

    // Coroutines
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    // UI
    implementation(Dependencies.UI.constraintLayout)
    implementation(Dependencies.UI.recyclerView)
    implementation(Dependencies.UI.progressbar)
    implementation(Dependencies.UI.fragmentKtx)
    implementation(Dependencies.UI.activityKtx)

    // Android
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.appCompatResources)
    implementation(Dependencies.Android.material)

    // Test
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Test.mockitoCore)
    androidTestImplementation(Dependencies.Test.mockitoKotlin)
}