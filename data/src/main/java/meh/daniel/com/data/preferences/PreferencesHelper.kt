//package meh.daniel.com.data.preferences
//
//import android.content.Context
//import android.content.SharedPreferences
//import javax.inject.Inject
//
//
//class PreferencesHelper @Inject constructor(private val context: Context) {
//
//    private val token: SharedPreferences =
//        context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
//
//
//
////    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
////        val editMe = edit()
////        operation(editMe)
////        editMe.apply()
////    }
////
////    var SharedPreferences.token
////        get() = getString(APP_PREFERENCES_TOKEN, "")
////        set(value) {
////            editMe {
////                it.putString(APP_PREFERENCES_TOKEN, value)
////            }
////        }
////
////    var SharedPreferences.clearValues
////        get() = { }
////        set(value) {
////            editMe {
////                it.clear()
////            }
////        }
////    fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
////        val key = pair.first
////        val value = pair.second
////        when (value) {
////            is String -> putString(key, value)
////            is Int -> putInt(key, value)
////            is Boolean -> putBoolean(key, value)
////            is Long -> putLong(key, value)
////            is Float -> putFloat(key, value)
////            else -> error("Only primitive types can be stored in SharedPreferences")
////        }
////    }
//
//
//
//
//}