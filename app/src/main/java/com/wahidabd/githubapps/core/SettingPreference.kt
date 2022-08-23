package com.wahidabd.githubapps.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class SettingPreference(context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val dataStore = context.dataStore

    fun getThemeSetting(): Flow<Boolean> =
        dataStore.data.map { pref ->
            pref[THEME_KEY] ?: false
        }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean){
        dataStore.edit { pref ->
            pref[THEME_KEY] = isDarkModeActive
        }
    }

    companion object{
        private val THEME_KEY = booleanPreferencesKey("theme_setting")

        @Volatile
        private var instance: SettingPreference? = null

        fun getInstance(context: Context): SettingPreference =
            instance ?: synchronized(this){
                instance ?: SettingPreference(context).also {
                    instance = it
                }
            }
    }

}