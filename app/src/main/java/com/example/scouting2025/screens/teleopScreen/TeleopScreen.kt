package com.example.scouting2025.screens.teleopScreen

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
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen
import com.example.scouting2025.screens.StandardComponents

@Composable
fun TeleopScreen(
    navigator: NavHostController,
    initialMatchData: MatchData
) {

    /* ----------------------------------------------------------------------------------------- */
    // State variables

    var matchData by remember { mutableStateOf(initialMatchData) }

    /* ----------------------------------------------------------------------------------------- */
    // Screen UI

    Scaffold(
        topBar = {
            // Top bar with a back button to the Auton screen
            StandardComponents.TopBar("Record Teleop") {
                navigator.navigate(NavScreen.AutonScreen(matchData))
            }
        },
        floatingActionButton = {
            // Extended floating action button to move to next screen
            StandardComponents.ContinueButton("Post-match") {
                navigator.navigate(NavScreen.PostMatchScreen(matchData))
            }
        }
    ) { innerPadding ->

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {

            /**
             * The Teleop screen will present ui to record the following state
             * - How much (and which level) coral was scored
             * - How much (and where) algae was scored
             */

            // Coral column
            StandardComponents.CoralColumn(
                box1 = matchData.teleopCoralL4,
                onBox1Change = { matchData = matchData.copy(teleopCoralL4 = it)},
                box2 = matchData.teleopCoralL3,
                onBox2Change = {matchData = matchData.copy(teleopCoralL3 = it)},
                box3 = matchData.teleopCoralL2,
                onBox3Change = {matchData = matchData.copy(teleopCoralL2 = it)},
                box4 = matchData.teleopCoralL1,
                onBox4Change = {matchData = matchData.copy(teleopCoralL1 = it)}
            )

            // Algae column
            StandardComponents.AlgaeColumn(
                box1 = matchData.teleopAlgaeProcessor,
                onBox1Change = {matchData = matchData.copy(teleopAlgaeProcessor = it)},
                box2 = matchData.teleopAlgaeNet,
                onBox2Change = {matchData = matchData.copy(teleopAlgaeNet = it)},
                box3 = matchData.teleopAlgaeRemoved,
                onBox3Change = {matchData = matchData.copy(teleopAlgaeRemoved = it)},
            )

        }
    }
}

