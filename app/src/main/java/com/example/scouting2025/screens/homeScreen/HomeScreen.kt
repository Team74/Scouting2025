package com.example.scouting2025.screens.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.scouting2025.R
import com.example.scouting2025.database.DeviceDataStore
import com.example.scouting2025.database.DeviceDataStore.Companion.DeviceModel
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    deviceDataStore: DeviceDataStore,
    navigator: NavHostController
) {

    /* NEVER DO THIS FOR PASSWORDS */
    val password = "l"

    /* ----------------------------------------------------------------------------------------- */
    // State variables

    val deviceModel by deviceDataStore.deviceModel.collectAsState(DeviceModel())

    var showAdminDialog: Boolean by remember { mutableStateOf(false) }
    var adminPassword: String by remember { mutableStateOf("") }

    /* ----------------------------------------------------------------------------------------- */
    // Screen UI

    if(showAdminDialog) {
        HomeComponents.AdminDialogue(
            adminPassword,
            onValueChange = {adminPassword = it},
            onDismissRequest = {showAdminDialog = it},
            onEnter = {if(it) navigator.navigate(NavScreen.AdminScreen)}
        )
    }

    Scaffold(
        topBar = {
            // Top bar with a title, button to admin, and login
            HomeComponents.TopBar(
                loggedIn = false,
                onAdminClick = { showAdminDialog = true },
                onLoginClick = {},
                onEditClick = { navigator.navigate(NavScreen.MatchListScreen) }
            )
        }
    ) { innerPadding ->

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.team_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(74.dp)
            )
            StartMatch {
                navigator.navigate(NavScreen.PreMatchScreen(MatchData()))
            }
        }

    }

}

@Composable
fun StartMatch(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxHeight(),
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge,
        contentPadding = PaddingValues(horizontal = 64.dp)
    ) {
        Text(
            text = "Start recording match",
            fontSize = 32.sp
        )
    }
}