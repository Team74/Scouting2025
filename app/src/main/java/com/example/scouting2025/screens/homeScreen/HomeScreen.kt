package com.example.scouting2025.screens.homeScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.scouting2025.database.AppDatabase

@Composable
fun HomeScreen(
    appDatabase: AppDatabase,
    navigator: NavHostController
) {

    Scaffold(
        topBar = { /* TODO: Top app bar here */ }
    ) { innerPadding ->

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // TODO: Put screen content here

        }

    }

}