package com.example.scouting2025.screens.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.scouting2025.R
import com.example.scouting2025.database.DeviceDataStore
import com.example.scouting2025.database.DeviceDataStore.Companion.DeviceModel
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.enums.Tablets
import com.example.scouting2025.screens.NavScreen
import com.example.scouting2025.screens.StandardComponents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    deviceDataStore: DeviceDataStore,
    navigator: NavHostController
) {

    /* ----------------------------------------------------------------------------------------- */
    // State variables

    val deviceModel by deviceDataStore.deviceModel.collectAsState(DeviceModel())

    var showAdminDialog: Boolean by remember { mutableStateOf(false) }
    var adminPassword: String by remember { mutableStateOf("") }

    /* ----------------------------------------------------------------------------------------- */
    // Screen UI

    if (showAdminDialog) {
        HomeComponents.AdminDialogue(
            adminPassword = adminPassword,
            onValueChange = {adminPassword = it},
            onDismissRequest = {showAdminDialog = it},
            onEnter = {if(it) navigator.navigate(NavScreen.AdminScreen)}
        )
    }

    Scaffold(
        topBar = {
            // Top bar with a title, button to admin, and login
            StandardComponents.TopBar(
                title = "Reefscape Scouting 2025",
                icon = Icons.Default.Settings,
                device = Tablets.strToTablet(deviceModel.device),
            ) { showAdminDialog = true }
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
            HomeComponents.StartMatchButton(Tablets.strToTablet(deviceModel.device)) {
                navigator.navigate(NavScreen.PreMatchScreen(MatchData()))
            }
        }

    }

}