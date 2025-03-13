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

    private fun matchListStringToList(str: String): List<Int> {
        return listOf()
    }

    companion object {
        data class DeviceModel(
            val device: String,
            val matchList: List<Int>
        )

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        private val DEVICE = stringPreferencesKey("device")
        private val MATCHLIST = stringPreferencesKey("matchList")
    }

    val deviceModel : Flow<DeviceModel> = context.dataStore.data
        .map { preferences ->
            DeviceModel(
                preferences[DEVICE] ?: "",
                matchListStringToList(preferences[MATCHLIST] ?: "")
            )
        }

    /* TODO: Just check the Medium article about DataStore */

    suspend fun saveDeviceData(deviceModel: DeviceModel) {
        context.dataStore.edit { preferences ->
            preferences[DEVICE] = deviceModel.device
            preferences[MATCHLIST] = deviceModel.matchList.toString()
        }
    }

}