package com.example.scouting2025.database

import android.content.Context
import androidx.room.Room

fun buildAppDatabase(appContext: Context): AppDatabase {
    return Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "reefscape-database"
    ).build()
}