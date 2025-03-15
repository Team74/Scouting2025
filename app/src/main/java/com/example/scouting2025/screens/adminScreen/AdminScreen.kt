package com.example.scouting2025.screens.adminScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.scouting2025.database.AppDatabase
import com.example.scouting2025.screens.StandardComponents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AdminScreen(
    appDatabase: AppDatabase,
    navigator: NavHostController,
    onExport: () -> Unit
) {

    //
    var showNukeDialog by remember { mutableStateOf(false) }

    if(showNukeDialog) {
        AdminComponents.NukeConfirmation(
            onDismissRequest = {showNukeDialog = false},
            onAccept = { nuke(appDatabase) }
        )
    }

    Scaffold(
        topBar = {StandardComponents.TopBar("Admin") {
            navigator.popBackStack()
        }}
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onExport,
                contentPadding = PaddingValues(74.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Export",
                    fontSize = 32.sp
                )
            }
            Button(
                onClick = {showNukeDialog = true},
                contentPadding = PaddingValues(74.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Nuke",
                    fontSize = 32.sp
                )
            }
        }
    }

}

private fun nuke(appDatabase: AppDatabase) {
    CoroutineScope(Dispatchers.IO).launch { appDatabase.matchDataDao().nuke() }
}