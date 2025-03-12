package com.example.scouting2025.screens.postMatchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.example.scouting2025.database.AppDatabase
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen

@Composable
fun PostMatchScreen(
    appDatabase: AppDatabase,
    navigator: NavHostController,
    initialMatchData: MatchData
) {
    var matchData by remember { mutableStateOf(initialMatchData) }

    Scaffold(
        topBar = { PostMatchComponents.PostmatchTopBar(onClick = {
            navigator.navigate(NavScreen.TeleopScreen(matchData))
        }) },
        floatingActionButton = {
            PostMatchComponents.PostmatchDone(onClick = {
                navigator.navigate(NavScreen.HomeScreen)
            })
        }
    ) { innerPadding ->

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //TODO put in prematch info to be recorded
        }
    }
}
