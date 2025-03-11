package com.example.scouting2025.screens

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.scouting2025.database.MatchData
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Suppress("MemberVisibilityCanBePrivate")
object MatchDataNavType {
    val MatchDataType = object : NavType<MatchData>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): MatchData? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): MatchData {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: MatchData): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: MatchData) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

    fun toTypeMap() = mapOf(typeOf<MatchData>() to MatchDataType)

}