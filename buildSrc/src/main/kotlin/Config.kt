import org.gradle.api.JavaVersion

object Config {

    const val compileSdk = 32
    const val minSDK = 22
    const val targetSDK = 31

    const val release = "release"
    const val debug = "debug"
    const val packageName = "meh.daniel.com.githubliteapp"

    object Options {
        val compileOptions = JavaVersion.VERSION_11
        const val kotlinOptions = "11"
    }

}