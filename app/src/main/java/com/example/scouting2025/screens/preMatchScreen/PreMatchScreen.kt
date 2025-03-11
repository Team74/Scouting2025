package com.example.scouting2025.screens.preMatchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen

@Composable
fun PreMatchScreen(
    navigator: NavHostController,
    initialMatchData: MatchData
) {

    var matchNumber by remember { mutableStateOf("") }
    var teamNumber by remember { mutableStateOf("") }
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
                matchNumber,
                "Match #"
            ) { matchNumber = it }
            PreMatchComponents.NumberTextbox(
                teamNumber,
                "Team #"
            ) { teamNumber = it }
        }
    }
}
