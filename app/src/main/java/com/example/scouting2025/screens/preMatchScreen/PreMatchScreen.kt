package com.example.scouting2025.screens.preMatchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen

@Composable
fun PreMatchScreen(
    navigator: NavHostController,
    initialMatchData: MatchData
) {

    var matchData by remember { mutableStateOf(initialMatchData) }

    Scaffold(
        topBar = { PreMatchComponents.PrematchTopBar(onClick = {
            navigator.navigate(NavScreen.HomeScreen)
        }) },
        floatingActionButton = {
            PreMatchComponents.PrematchDone(onClick = {
                navigator.navigate(NavScreen.AutonScreen(matchData))
            })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(160.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            PreMatchComponents.NumberTextbox(
                matchData.matchNumber,
                "Match #"
            ) { matchData = matchData.copy(matchNumber = it) }
            PreMatchComponents.NumberTextbox(
                matchData.teamNumber,
                "Team #"
            ) { matchData = matchData.copy(teamNumber = it) }
        }
    }
}
