package com.example.scouting2025.screens.homeScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scouting2025.screens.StandardComponents

@OptIn(ExperimentalMaterial3Api::class)
object HomeComponents {

    @Composable
    fun TopBar(
        loggedIn: Boolean,
        onAdminClick: () -> Unit,
        onLoginClick: () -> Unit,
        onEditClick: () -> Unit
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
                TextButton(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = if (loggedIn) "Sign Out" else "Login",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (loggedIn) StandardComponents.SimpleIconButton(
                    icon = Icons.Default.Edit,
                    onClick = onEditClick
                )
            }
        )
    }

}