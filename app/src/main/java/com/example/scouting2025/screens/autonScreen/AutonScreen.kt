package com.example.scouting2025.screens.autonScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
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
import com.example.scouting2025.database.AppDatabase
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.screens.NavScreen

@Composable
fun AutonScreen(
    appDatabase: AppDatabase,
    navigator: NavHostController
) {

    var autoL1Coral by remember { mutableIntStateOf(0) }
    var autoL2Coral by remember { mutableIntStateOf(0) }
    var autoL3Coral by remember { mutableIntStateOf(0) }
    var autoL4Coral by remember { mutableIntStateOf(0) }
    var autoProcessorAlgae by remember { mutableIntStateOf(0) }
    var autoNetAlgae by remember { mutableIntStateOf(0) }
    var autoMoved by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AutonTopBar(onClick = {
            navigator.navigate(NavScreen.PrematchScreen(
                MatchData()
            ))
        }) },
        floatingActionButton = {
            AutonDone(onClick = {
                navigator.navigate(NavScreen.TeleopScreen)
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
            MovedCheckBox(
                input = autoMoved
            ) {autoMoved = it}
            CoralColumn(
                box1 = autoL4Coral,
                onBox1Change = {autoL4Coral = it},
                box2 = autoL3Coral,
                onBox2Change = {autoL3Coral = it},
                box3 = autoL2Coral,
                onBox3Change = {autoL2Coral = it},
                box4 = autoL1Coral,
                onBox4Change = {autoL1Coral = it}
            )
            AlgaeColumn(
                box1 = autoProcessorAlgae,
                onBox1Change = {autoProcessorAlgae = it},
                box2 = autoNetAlgae,
                onBox2Change = {autoNetAlgae = it},
            )
        }
    }
}

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
fun CoralColumn(
    modifier: Modifier = Modifier,
    box1: Int,
    onBox1Change: (Int) -> Unit,
    box2: Int,
    onBox2Change: (Int) -> Unit,
    box3: Int,
    onBox3Change: (Int) -> Unit,
    box4: Int,
    onBox4Change: (Int) -> Unit,
) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "4 levels of coral",
            fontSize = 24.sp
        )
        IncrementingBox(
            input = box1
        ) {onBox1Change(it)}
        IncrementingBox(
            input = box2
        ) {onBox2Change(it)}
        IncrementingBox(
            input = box3
        ) {onBox3Change(it)}
        IncrementingBox(
            input = box4
        ) {onBox4Change(it)}
    }
}

@Composable
fun AlgaeColumn(
    modifier: Modifier = Modifier,
    box1: Int,
    onBox1Change: (Int) -> Unit,
    box2: Int,
    onBox2Change: (Int) -> Unit,
) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "2 types of algae",
            fontSize = 24.sp
        )
        IncrementingBox(
            input = box1
        ) {onBox1Change(it)}
        IncrementingBox(
            input = box2
        ) {onBox2Change(it)}
    }
}

@Composable
fun IncrementingBox (
    modifier: Modifier = Modifier,
    input: Int,
    onValueChange: (Int) -> Unit
) {

    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallFloatingActionButton(
            onClick = {
                if (input > 0) onValueChange(input - 1)
            }
        ) {
            Text("-")
        }
        SmallFloatingActionButton(
                onClick = {
                    onValueChange(input + 1)
                }
        ) {
            Text("+")
        }
        Text(
            text = input.toString(),
            fontSize = 24.sp
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