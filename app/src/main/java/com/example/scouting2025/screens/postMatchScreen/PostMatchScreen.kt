package com.example.scouting2025.screens.postMatchScreen

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
import com.example.scouting2025.database.AppDatabase
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen
import com.example.scouting2025.screens.StandardComponents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PostMatchScreen(
    appDatabase: AppDatabase,
    navigator: NavHostController,
    initialMatchData: MatchData
) {

    /* ----------------------------------------------------------------------------------------- */
    // State variables

    var matchData by remember { mutableStateOf(initialMatchData) }

    var dropdownExpanded by remember { mutableStateOf(false ) }

    /* ----------------------------------------------------------------------------------------- */
    // Screen UI

    Scaffold(
        topBar = {
            // Top bar with a back button to the Teleop screen
            StandardComponents.TopBar("Post-match") {
                navigator.navigate(NavScreen.TeleopScreen(matchData))
            }
        },
        floatingActionButton = {
            // Extended floating action button to move to add the match data to the database and
            // navigate back to the home screen
            StandardComponents.ContinueButton("Finish") {
                saveToDatabase(appDatabase, matchData)
                navigator.popBackStack(NavScreen.HomeScreen, inclusive = false)
            }
        }
    ) { innerPadding ->

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 256.dp, vertical = 64.dp)
        ) {

            /**
             * The PostMatch screen will present the ui to record the following state
             * - End of match climb state
             * - Did the robot disable at all during the match?
             * - Any additional notes about the match
             */

            // Climb state dropdown box
            PostMatchComponents.ClimbStateDropDown(
                state = matchData.climbState,
                onStateChange = { matchData = matchData.copy(climbState = it) },
                expanded = dropdownExpanded,
                onExpandedChange = { dropdownExpanded = it }
            )

            // Did robot disable check box
            StandardComponents.LabeledCheckBox(
                state = matchData.didRobotDisable,
                onStateChange = { matchData = matchData.copy(didRobotDisable = it) },
                label = "Did the robot disable?",
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            // Addition notes text field
            PostMatchComponents.AdditionalNotesField(
                text = matchData.additionalNotes,
                onTextChange = { matchData = matchData.copy(additionalNotes = it) }
            )

        }
    }
}


private fun saveToDatabase(appDatabase: AppDatabase, matchData: MatchData) {
    CoroutineScope(Dispatchers.IO).launch {
        appDatabase.matchDataDao().update(matchData)
    }
}
