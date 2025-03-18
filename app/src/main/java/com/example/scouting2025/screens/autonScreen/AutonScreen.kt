package com.example.scouting2025.screens.autonScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun AutonScreen(
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
            // Top bar with a back button to the PreMatch screen
            StandardComponents.TopBar("Record Auton") {
                navigator.navigate(NavScreen.PreMatchScreen(matchData))
            }
        },
        floatingActionButton = {
            // Extended floating action button to move to next screen
            StandardComponents.ContinueButton("Teleop") {
                navigator.navigate(NavScreen.TeleopScreen(matchData))
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
             * The Auton screen will present ui to record the following state
             * - Whether the robot left the starting area
             * - How much (and which level) coral was scored
             * - How much (and where) algae was scored
             */

            // Coral column
            StandardComponents.CoralColumn(
                box1 = matchData.autonCoralL4,
                onBox1Change = {matchData = matchData.copy(autonCoralL4 = it)},
                box2 = matchData.autonCoralL3,
                onBox2Change = {matchData = matchData.copy(autonCoralL3 = it)},
                box3 = matchData.autonCoralL2,
                onBox3Change = {matchData = matchData.copy(autonCoralL2 = it)},
                box4 = matchData.autonCoralL1,
                onBox4Change = {matchData = matchData.copy(autonCoralL1 = it)},
                isAuton = true
            )

            Column (
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                // Algae column
                StandardComponents.AlgaeColumn(
                    box1 = matchData.autonAlgaeProcessor,
                    onBox1Change = {matchData = matchData.copy(autonAlgaeProcessor = it)},
                    box2 = matchData.autonAlgaeNet,
                    onBox2Change = {matchData = matchData.copy(autonAlgaeNet = it)},
                    box3 = matchData.autonAlgaeRemoved,
                    onBox3Change = {matchData = matchData.copy(autonAlgaeRemoved = it)},
                    isAuton = true
                )

                // Did robot leave starting area
                StandardComponents.LabeledCheckBox(
                    state = matchData.autonLeave,
                    onStateChange = { matchData = matchData.copy(autonLeave = it) },
                    label = "Did robot leave starting area?"
                )
            }

        }
    }
}

