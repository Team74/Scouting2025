package com.example.scouting2025.screens.preMatchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.scouting2025.database.DeviceDataStore
import com.example.scouting2025.database.DeviceDataStore.Companion.DeviceModel
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.enums.Tablets
import com.example.scouting2025.screens.NavScreen
import com.example.scouting2025.screens.StandardComponents

@Composable
fun PreMatchScreen(
    deviceDataStore: DeviceDataStore,
    navigator: NavHostController,
    initialMatchData: MatchData
) {

    /* ----------------------------------------------------------------------------------------- */
    // State variables

    val deviceModel by deviceDataStore.deviceModel.collectAsState(DeviceModel())

    var matchData by remember {
        mutableStateOf(initialMatchData.copy(onShift = deviceModel.loggedIn))
    }

    // These will be used determine if the text fields have been edited yet and will only be true
    // when their respective fields have been.
    // We can use this to show an error if someone edits a text box but deletes everything because
    // we want both text boxes to be filled before we can move to the next screen.
    var wasMatchNumberEdited by remember { mutableStateOf(false) }
    var wasTeamNumberEdited by remember { mutableStateOf(false) }

    // Fancy was to declare a variable that will reflect the state of the calculation inside of it.
    // In this case, it will be true that the match number text box has an error if it has
    // previously been edited AND is currently blank,
    val isMatchNumberError by remember {
        derivedStateOf { wasMatchNumberEdited && matchData.matchNumber.isEmpty() }
    }
    val isTeamNumberError by remember {
        derivedStateOf { wasTeamNumberEdited && matchData.teamNumber.isEmpty() }
    }

    /* ----------------------------------------------------------------------------------------- */
    // Screen UI

    Scaffold(
        topBar = {
            // Top bar with a title and a back button
            StandardComponents.TopBar(
                title = "PreMatch",
                device = Tablets.strToTablet(deviceModel.device)
            ) { navigator.popBackStack(NavScreen.HomeScreen, inclusive = false) }
        },
        floatingActionButton = {
            // Extended floating action button to move to next screen
            StandardComponents.ContinueButton("Auton") {
                // Only allow a continue if both fields have input
                if (matchData.matchNumber.isNotEmpty() && matchData.teamNumber.isNotEmpty()) {
                    navigator.navigate(NavScreen.AutonScreen(matchData))
                }
            }
        }
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 256.dp, vertical = 64.dp)
        ) {

            /**
             * The PreMatch screen will present ui to record the following state
             * - The current match number
             * - The current team the tablet is assigned to
             */

            // Explanation
            Text("Before a match starts, please record the current match number and team " +
                    "number you will be scouting",
                textAlign = TextAlign.Center)

            // Match number
            StandardComponents.NumberTextField(
                text = matchData.matchNumber,
                onTextChange = {
                    matchData = matchData.copy(matchNumber = it)
                    wasMatchNumberEdited = true
                },
                label = "Match Number",
                leadingIcon = Icons.Default.Star,
                isError = isMatchNumberError,
                onErrorText = "A match number is required to move forward!",
                maxDigits = 2,
                modifier = Modifier
                    .fillMaxWidth()
            )
            // Team number
            StandardComponents.NumberTextField(
                text = matchData.teamNumber,
                onTextChange = {
                    matchData = matchData.copy(teamNumber = it)
                    wasTeamNumberEdited = true
                },
                label = "Team Number",
                leadingIcon = Icons.Default.Star,
                isError = isTeamNumberError,
                onErrorText = "A team number is required to move forward!",
                maxDigits = 5,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
    }
}
