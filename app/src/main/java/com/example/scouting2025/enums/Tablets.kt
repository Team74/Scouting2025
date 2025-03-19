package com.example.scouting2025.enums

enum class Tablets(
    val label: String
) {
    R1("Red 1"),
    R2("Red 2"),
    R3("Red 3"),
    B1("Blue 1"),
    B2("Blue 2"),
    B3("Blue 3");

    companion object {
        fun strToTablet(str: String): Tablets {
            return Tablets.entries.firstOrNull {
                it.name == str
            } ?: B1
        }
    }
}