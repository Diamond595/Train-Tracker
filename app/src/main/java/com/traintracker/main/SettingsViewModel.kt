package com.traintracker.main

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Context.datastore by preferencesDataStore(name = "settings")


data class Settings (
    val darkTheme: Boolean,
    val language: String,
    val accessToken: String,
    val textSizeMultiplier: Float,
    val iconStyle: Int
) {
    companion object {
        fun default(context: Context): Settings {
            return Settings(
                darkTheme = true,
                language = "en",
                accessToken = "",
                textSizeMultiplier = 1f,
                iconStyle = 0
            )
        }
    }
}

class SettingsManager(
    private val context: Context
) {
    private object Keys {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val LANGUAGE = stringPreferencesKey("language")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val TEXT_SIZE_MULTIPLIER = floatPreferencesKey("text_size_multiplier")
        val ICON_STYLE = intPreferencesKey("icon_style")
    }

    val settingsFlow: Flow<Settings> = context.datastore.data.map { prefs ->
        Settings(
            darkTheme = prefs[Keys.DARK_THEME] ?: false,
            language = prefs[Keys.LANGUAGE] ?: "en",
            accessToken = prefs[Keys.ACCESS_TOKEN] ?: "",
            textSizeMultiplier = prefs[Keys.TEXT_SIZE_MULTIPLIER] ?: 1f,
            iconStyle = prefs[Keys.ICON_STYLE] ?: 0
        )
    }

    suspend fun setDarkTheme(enabled: Boolean){
        context.datastore.edit { prefs ->
            prefs[Keys.DARK_THEME] = enabled
        }
    }

    suspend fun setLanguage(language: String){
        context.datastore.edit { prefs ->
            prefs[Keys.LANGUAGE] = language
        }
    }

    suspend fun setAccessToken(accessToken: String){
        context.datastore.edit { prefs ->
            prefs[Keys.ACCESS_TOKEN] = accessToken
        }
    }

    suspend fun setTextSizeMultiplier(textSizeMultiplier: Float){
        context.datastore.edit { prefs ->
            prefs[Keys.TEXT_SIZE_MULTIPLIER] = textSizeMultiplier
        }
    }

    suspend fun setIconStyle(iconStyle: Int){
        context.datastore.edit { prefs ->
            prefs[Keys.ICON_STYLE] = iconStyle
        }
    }
}

class SettingsViewModel(
    private val settingsManager: SettingsManager
) : ViewModel(){
    val settingsFlow = settingsManager.settingsFlow

    fun toggleTheme(isDark: Boolean){
        viewModelScope.launch {
            settingsManager.setDarkTheme(isDark)
        }
    }

    fun setLanguage(language: String){
        viewModelScope.launch {
            settingsManager.setLanguage(language)
        }
    }

    fun setAccessToken(accessToken: String){
        viewModelScope.launch {
            settingsManager.setAccessToken(accessToken)
        }
    }

    fun setTextSizeMultiplier(textSizeMultiplier: Float){
        viewModelScope.launch {
            settingsManager.setTextSizeMultiplier(textSizeMultiplier)
        }
    }

    fun setIconStyle(iconStyle: Int){
        viewModelScope.launch {
            settingsManager.setIconStyle(iconStyle)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                SettingsViewModel(SettingsManager(application))
            }
        }
    }
}
