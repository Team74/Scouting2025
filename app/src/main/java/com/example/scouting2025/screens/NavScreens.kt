package com.example.scouting2025.screens

import com.example.scouting2025.database.MatchData
import kotlinx.serialization.Serializable

sealed class NavScreen {

    @Serializable
    data object HomeScreen : NavScreen()

    @Serializable
    data class PreMatchScreen(
        val matchData: MatchData
    ) : NavScreen()

    @Serializable
    data class AutonScreen(
        val matchData: MatchData
    ) : NavScreen()

    @Serializable
    data class TeleopScreen(
        val matchData: MatchData
    ) : NavScreen()

    @Serializable
    data class PostMatchScreen(
        val matchData: MatchData
    ) : NavScreen()

    @Serializable
    data object ShiftScreen : NavScreen()

    @Serializable
    data object RevisionScreen : NavScreen()

    @Serializable
    data object AdminScreen : NavScreen()

}