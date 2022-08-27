object Dependencies {
    // Hilt
    private const val versionHilt = "2.40.5"
    // Lifecycle
    private const val versionLifecycle = "2.4.0-alpha03"
    // Navigation
    private const val versionNavigation = "2.5.0"
    // Coroutines
    private const val versionCoroutines = "1.6.0"
    // Network
    private const val versionRetrofit2 = "2.6.2"
    private const val versionLogging = "4.2.1"
    // UI
    private const val versionConstraintLayout = "2.1.4"
    private const val versionRecyclerView = "1.2.1"
    private const val fragmentKtxVersion = "1.5.0"
    private const val activityKtxVersion = "1.5.0"
    // Android
    private const val versionAndroidCoreKtx = "1.7.0"
    private const val versionAndroidAppcompat = "1.4.2"
    private const val versionAndroidMaterial = "1.6.1"
    // Room
    private const val versionRoom = "2.3.0"
    private const val versionRoomPagind = "2.4.0-alpha04"
    // GeoLocation
    private const val versionGeoLocation = "20.0.0"
    // Test
    private const val versionTestJUnit = "4.+"
    private const val versionTestAndroidxJUnit = "1.1.2"
    private const val versionTestEspresso = "3.3.0"



    object Hilt {
        const val android = "com.google.dagger:hilt-android:$versionHilt"
        const val compiler = "com.google.dagger:hilt-compiler:$versionHilt"
    }

    object Lifecycle {
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$versionLifecycle"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$versionLifecycle"
    }

    object Room {
        const val ktx = "androidx.room:room-ktx:$versionRoom"
        const val runtime = "androidx.room:room-runtime:$versionRoom"
        const val paging = "androidx.room:room-paging:$versionRoomPagind"
        const val compiler = "androidx.room:room-compiler:$versionRoom"
    }

    object Geo {
        const val playServices = "com.google.android.gms:play-services-location:$versionGeoLocation"
    }

    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment:$versionNavigation"
        const val ui = "androidx.navigation:navigation-ui:$versionNavigation"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$versionCoroutines"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$versionCoroutines"
    }

    object Network {
        const val retrofit2 = "com.squareup.retrofit2:retrofit:$versionRetrofit2"
        const val retrofit2Gson = "com.squareup.retrofit2:converter-gson:$versionRetrofit2"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$versionLogging"
    }

    object UI {
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$versionConstraintLayout"
        const val recyclerView = "androidx.recyclerview:recyclerview:$versionRecyclerView"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentKtxVersion"
        const val activityKtx = "androidx.activity:activity-ktx:$activityKtxVersion"
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:$versionAndroidCoreKtx"
        const val appCompat = "androidx.appcompat:appcompat:$versionAndroidAppcompat"
        const val appCompatResources = "androidx.appcompat:appcompat:$versionAndroidAppcompat"
        const val material = "com.google.android.material:material:$versionAndroidMaterial"
    }

    object Test {
        const val jUnit = "junit:junit:$versionTestJUnit"
        const val androidJUnit = "androidx.test.ext:$versionTestAndroidxJUnit"
        const val espresso = "androidx.test.espresso:espresso-core:$versionTestEspresso"
    }

}