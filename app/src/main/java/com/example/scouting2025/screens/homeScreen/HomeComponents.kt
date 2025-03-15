package com.example.scouting2025.screens.homeScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
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

    @Composable
    fun AdminDialogue(
        adminPassword: String,
        onValueChange: (String) -> Unit,
        onDismissRequest: (Boolean) -> Unit,
        onEnter: (Boolean) -> Unit
    ) {
        BasicAlertDialog(
            onDismissRequest = {onDismissRequest(false)}
        ) {
            Card {
                Text(
                    text = "Admin password",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = adminPassword,
                    onValueChange = onValueChange,
                    label = {Text("Password")},
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = false,
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        val password = "l"
                        onEnter(password == adminPassword)
                    }),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
        }
    }

}