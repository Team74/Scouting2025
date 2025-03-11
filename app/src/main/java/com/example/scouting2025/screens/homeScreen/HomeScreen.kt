package com.example.scouting2025.screens.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen

@Composable
fun HomeScreen(
    navigator: NavHostController
) {

    Scaffold(
        topBar = { HomeScreenTopBar(onClick = {
            navigator.navigate(NavScreen.AdminScreen)
        }) }
    ) { innerPadding ->

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier)
            StartMatch {
                navigator.navigate(NavScreen.PreMatchScreen(MatchData()))
            }
        }

    }

}

@Composable
fun StartMatch(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxHeight(),
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge,
        contentPadding = PaddingValues(horizontal = 64.dp)
    ) {
        Text(
            text = "Record match",
            fontSize = 32.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text("Home Screen")
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