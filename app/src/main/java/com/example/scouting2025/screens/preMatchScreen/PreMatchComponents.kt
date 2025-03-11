package com.example.scouting2025.screens.preMatchScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

object PreMatchComponents {
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

    @Composable
    fun NumberTextbox(
        string: String,
        label: String,
        onValueChange: (String) -> Unit
    ) {
        OutlinedTextField(
            value = string,
            onValueChange = { newText ->
                if (newText.isDigitsOnly()) {
                    onValueChange(newText)
                }
            },
            label = {
                Text(label)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}