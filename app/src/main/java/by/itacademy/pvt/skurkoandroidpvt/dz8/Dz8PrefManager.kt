package by.itacademy.pvt.skurkoandroidpvt.dz8

import android.content.Context
import android.content.SharedPreferences

private val SHARED_PREFS_NAME = "PREF_NAME"
private val TEXT_KEY = "USER_TEXT"

class Dz8PrefManager(context: Context) {

    private val sharedPrefs: SharedPreferences = context
        .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUserText(text: String) {
        sharedPrefs
            .edit()
            .putString(TEXT_KEY, text)
            .apply()
    }

    fun getUserText(): String {
        return sharedPrefs.getString(TEXT_KEY, "") ?: ""
    }
}