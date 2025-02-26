package com.example.scouting2025

enum class ClimbState {

    DID_NOT_MAKE_IT,
    PARKED,
    DEEP,
    SHALLOW;

    companion object {
        fun fromInt(value: Int) = entries.first { it.ordinal == value }
    }

}