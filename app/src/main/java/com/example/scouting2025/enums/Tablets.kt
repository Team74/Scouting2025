package com.example.scouting2025.enums

enum class Tablets {
    R1,
    R2,
    R3,
    B1,
    B2,
    B3;

    companion object {
        fun strToTablet(str: String): Tablets {
            return Tablets.entries.firstOrNull {
                it.name == str
            } ?: B1
        }
    }
}