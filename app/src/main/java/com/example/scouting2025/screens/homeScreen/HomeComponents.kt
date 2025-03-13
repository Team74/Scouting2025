package com.example.scouting2025.screens.homeScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.scouting2025.screens.StandardComponents

@OptIn(ExperimentalMaterial3Api::class)
object HomeComponents {

    @Composable
    fun TopBar(
        onAdminClick: () -> Unit,
        onLoginClick: () -> Unit
    ) {
        TopAppBar(
            title = { Text("Reefscape Scouting") },
            navigationIcon = {
                StandardComponents.SimpleIconButton(
                    icon = Icons.Default.Settings,
                    onClick = onAdminClick
                )
            },
            actions = {
                StandardComponents.SimpleIconButton(
                    icon = Icons.Default.AccountCircle,
                    onClick = onLoginClick
                )
            }
        )
    }

}