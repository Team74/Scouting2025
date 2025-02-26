package com.example.scouting2025.database

import androidx.room.TypeConverter
import com.example.scouting2025.ClimbState

class Converters {

    @TypeConverter
    fun fromClimbState(state: ClimbState) = state.ordinal

    @TypeConverter
    fun toClimbState(value: Int): ClimbState = ClimbState.fromInt(value)

}