package meh.daniel.com.data.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("token", Context.MODE_PRIVATE)

    private fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }
}