package com.example.scouting2025.screens.prematchScreen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.scouting2025.database.AppDatabase
import com.example.scouting2025.screens.NavScreen

@Composable
fun PrematchScreen(
    appDatabase: AppDatabase,
    navigator: NavHostController
) {

    Scaffold(
        topBar = { PrematchTopBar(onClick = {
            navigator.navigate(NavScreen.HomeScreen)
        }) },
        floatingActionButton = {
            PrematchDone(onClick = {
                navigator.navigate(NavScreen.AutonScreen)
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

@Composable
fun PrematchDone(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Text(
            text = "To auton",
            fontSize = 20.sp
        )
        Icon(
            Icons.AutoMirrored.Filled.Send,
            null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrematchTopBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text("Pre-match info")
        },
        navigationIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    null
                )
            }
        }
    )
}