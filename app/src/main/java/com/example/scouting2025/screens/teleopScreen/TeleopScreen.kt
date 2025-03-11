package com.example.scouting2025.screens.teleopScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.ScreenComponents

@Composable
fun TeleopScreen(
    navigator: NavHostController,
    initialMatchData: MatchData
) {

    var teleopL1Coral by remember { mutableIntStateOf(0) }
    var teleopL2Coral by remember { mutableIntStateOf(0) }
    var teleopL3Coral by remember { mutableIntStateOf(0) }
    var teleopL4Coral by remember { mutableIntStateOf(0) }
    var teleopProcessorAlgae by remember { mutableIntStateOf(0) }
    var teleopNetAlgae by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { TeleopComponents.TeleopTopBar(onClick = {
            //navigator.navigate(NavScreen.AutonScreen)
        }) },
        floatingActionButton = {
            TeleopComponents.TeleopDone(onClick = {
                //navigator.navigate(NavScreen.PostMatchScreen)
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
            ScreenComponents.CoralColumn(
                box1 = teleopL4Coral,
                onBox1Change = {teleopL4Coral = it},
                box2 = teleopL3Coral,
                onBox2Change = {teleopL3Coral = it},
                box3 = teleopL2Coral,
                onBox3Change = {teleopL2Coral = it},
                box4 = teleopL1Coral,
                onBox4Change = {teleopL1Coral = it}
            )
            ScreenComponents.AlgaeColumn(
                box1 = teleopProcessorAlgae,
                onBox1Change = {teleopProcessorAlgae = it},
                box2 = teleopNetAlgae,
                onBox2Change = {teleopNetAlgae = it},
            )
        }
    }
}

