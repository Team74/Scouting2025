package com.example.scouting2025.screens

import com.example.scouting2025.database.MatchData
import kotlinx.serialization.Serializable


// TODO: Change screen data objects to data classes relative to what data is being passed to each screen
sealed class NavScreen {

    @Serializable
    data object HomeScreen : NavScreen()

    @Serializable
    data class PrematchScreen(
        val matchData: MatchData
    ) : NavScreen()

    @Serializable
    data object AutonScreen : NavScreen()

    @Serializable
    data object TeleopScreen : NavScreen()

    @Serializable
    data object PostmatchScreen : NavScreen()

    @Serializable
    data object ShiftScreen : NavScreen()

    @Serializable
    data object RevisionScreen : NavScreen()

    @Serializable
    data object AdminScreen : NavScreen()

}