package com.example.scouting2025.screens.autonScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen
import com.example.scouting2025.screens.ScreenComponents

@Composable
fun AutonScreen(
    navigator: NavHostController,
    initialMatchData: MatchData
) {

    var autoL1Coral by remember { mutableIntStateOf(0) }
    var autoL2Coral by remember { mutableIntStateOf(0) }
    var autoL3Coral by remember { mutableIntStateOf(0) }
    var autoL4Coral by remember { mutableIntStateOf(0) }
    var autoProcessorAlgae by remember { mutableIntStateOf(0) }
    var autoNetAlgae by remember { mutableIntStateOf(0) }
    var autoMoved by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AutonComponents.AutonTopBar(onClick = {
            navigator.navigate(NavScreen.PreMatchScreen(
                MatchData()
            ))
        }) },
        floatingActionButton = {
            AutonComponents.AutonDone(onClick = {
                navigator.navigate(NavScreen.TeleopScreen(initialMatchData))
            })
        }
    ) { innerPadding ->

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            AutonComponents.MovedCheckBox(
                input = autoMoved
            ) {autoMoved = it}
            ScreenComponents.CoralColumn(
                box1 = autoL4Coral,
                onBox1Change = {autoL4Coral = it},
                box2 = autoL3Coral,
                onBox2Change = {autoL3Coral = it},
                box3 = autoL2Coral,
                onBox3Change = {autoL2Coral = it},
                box4 = autoL1Coral,
                onBox4Change = {autoL1Coral = it}
            )
            ScreenComponents.AlgaeColumn(
                box1 = autoProcessorAlgae,
                onBox1Change = {autoProcessorAlgae = it},
                box2 = autoNetAlgae,
                onBox2Change = {autoNetAlgae = it},
            )
        }
    }
}

