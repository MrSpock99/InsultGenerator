package itis.ru.insultgenerator.model

import android.content.Context
import android.content.Context.MODE_PRIVATE

private const val APP_SETTINGS: String = "APP_SETTINGS"
private const val PREF_PAGINATION_SIZE: String = "PREF_PAGINATION_SIZE"

class SettingsInteractor(private val context: Context) {
    private val preferences = context.getSharedPreferences(APP_SETTINGS, MODE_PRIVATE)

    fun savePaginationSize(size: Int) {
        val editor = preferences?.edit()
        editor?.putInt(PREF_PAGINATION_SIZE, size)
        editor?.apply()
    }

    fun getPaginationSize(): Int? = preferences?.getInt(PREF_PAGINATION_SIZE, 0)
}
