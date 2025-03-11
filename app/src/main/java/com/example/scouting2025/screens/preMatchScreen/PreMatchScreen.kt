package com.example.scouting2025.screens.preMatchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen

@Composable
fun PreMatchScreen(
    navigator: NavHostController,
    initialMatchData: MatchData
) {

    var matchNumber by remember { mutableStateOf("") }
    var teamNumber by remember { mutableStateOf("") }
    Scaffold(
        topBar = { PrematchTopBar(onClick = {
            navigator.navigate(NavScreen.HomeScreen)
        }) },
        floatingActionButton = {
            PrematchDone(onClick = {
                navigator.navigate(NavScreen.AutonScreen(initialMatchData))
            })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(160.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            NumberTextbox(
                matchNumber,
                "Match #"
            ) { matchNumber = it }
            NumberTextbox(
                teamNumber,
                "Team #"
            ) { teamNumber = it }
        }
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