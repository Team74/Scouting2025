package com.example.scouting2025.screens.autonScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

object AutonComponents {
    @Composable
    fun MovedCheckBox(
        modifier: Modifier = Modifier,
        input: Boolean,
        onValueChange: (Boolean) -> Unit
    ) {
        var checked = input

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Did robot leave start?",
                softWrap = true,
                fontSize = 16.sp
            )
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )
        }
    }


    @Composable
    fun AutonDone(onClick: () -> Unit) {
        ExtendedFloatingActionButton(
            onClick = onClick,
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Text(
                text = "To teleop",
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
    fun AutonTopBar(
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        TopAppBar(
            title = {
                Text("Recording Auton")
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