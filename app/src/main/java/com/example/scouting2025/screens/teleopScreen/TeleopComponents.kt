package com.example.scouting2025.screens.teleopScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

object TeleopComponents {
    @Composable
    fun TeleopDone(onClick: () -> Unit) {
        ExtendedFloatingActionButton(
            onClick = onClick,
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Text(
                text = "To post-match",
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
    fun TeleopTopBar(
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        TopAppBar(
            title = {
                Text("Recording Teleop")
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
}