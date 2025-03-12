package com.example.scouting2025.screens.autonScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen
import com.example.scouting2025.screens.ScreenComponents

@Composable
fun AutonScreen(
    navigator: NavHostController,
    initialMatchData: MatchData
) {
    var matchData by remember { mutableStateOf(initialMatchData) }

    Scaffold(
        topBar = { AutonComponents.AutonTopBar(onClick = {
            navigator.navigate(NavScreen.PreMatchScreen(matchData))
        }) },
        floatingActionButton = {
            AutonComponents.AutonDone(onClick = {
                navigator.navigate(NavScreen.TeleopScreen(matchData))
            })
        }
    ) { innerPadding ->

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            AutonComponents.MovedCheckBox(
                input = matchData.autonLeave
            ) {matchData = matchData.copy(autonLeave = it)}
            ScreenComponents.CoralColumn(
                box1 = matchData.autonCoralL4,
                onBox1Change = {matchData = matchData.copy(autonCoralL4 = it)},
                box2 = matchData.autonCoralL3,
                onBox2Change = {matchData = matchData.copy(autonCoralL3 = it)},
                box3 = matchData.autonCoralL2,
                onBox3Change = {matchData = matchData.copy(autonCoralL2 = it)},
                box4 = matchData.autonCoralL1,
                onBox4Change = {matchData = matchData.copy(autonCoralL1 = it)}
            )
            ScreenComponents.AlgaeColumn(
                box1 = matchData.autonAlgaeProcessor,
                onBox1Change = {matchData = matchData.copy(autonAlgaeProcessor = it)},
                box2 = matchData.autonAlgaeNet,
                onBox2Change = {matchData = matchData.copy(autonAlgaeNet = it)},
            )
        }
    }
}

