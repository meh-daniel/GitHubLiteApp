object Dependencies {
    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object DataStore  {
        const val store = "androidx.datastore:datastore-preferences:1.0.0"
    }

    object Lifecycle {
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object Javax {
        const val inject = "javax.inject:javax.inject:1"
    }

    object Room {
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val paging = "androidx.room:room-paging:${Versions.roomPaging}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Geo {
        const val playServices = "com.google.android.gms:play-services-location:${Versions.geoLocation}"
    }

    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
        const val ui = "androidx.navigation:navigation-ui:${Versions.navigation}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Network {
        const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
        const val retrofit2Gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.logging}"
    }

    object UI {
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val progressbar = "com.mikhaellopez:circularprogressbar:${Versions.progressbar}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidCoreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidAppcompat}"
        const val appCompatResources = "androidx.appcompat:appcompat:${Versions.androidAppcompat}"
        const val material = "com.google.android.material:material:${Versions.androidMaterial}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.testJUnit}"
        const val androidJUnit = "androidx.test.ext:${Versions.testJUnit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.testEspresso}"
        const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
        const val common = "androidx.paging:paging-common:${Versions.paging}"
    }
}