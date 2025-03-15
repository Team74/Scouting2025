package com.example.scouting2025.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DeviceDataStore(
    private val context: Context
) {

    /* ----------------------------------------------------------------------------------------- */
    // Companion stuff idk

    companion object {
        data class DeviceModel(
            val device: String = "",
            val loggedIn: String = "",
        )

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        private val DEVICE = stringPreferencesKey("device")
        private val LOGIN = stringPreferencesKey("loggedIn")
    }

    /* ----------------------------------------------------------------------------------------- */
    // State

    val deviceModel : Flow<DeviceModel> = context.dataStore.data.map { preferences ->
            DeviceModel(
                preferences[DEVICE] ?: "",
                preferences[LOGIN] ?: ""
            )
        }

    /* ----------------------------------------------------------------------------------------- */
    // Update functions

    suspend fun setDevice(deviceModel: DeviceModel) {
        context.dataStore.edit { it[DEVICE] = deviceModel.device }
    }
    suspend fun setLogin(deviceModel: DeviceModel) {
        context.dataStore.edit { it[LOGIN] = deviceModel.loggedIn }
    }

}