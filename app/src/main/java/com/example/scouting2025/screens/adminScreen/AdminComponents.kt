package com.example.scouting2025.screens.adminScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AdminComponents {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NukeConfirmation(
        modifier: Modifier = Modifier,
        onDismissRequest: () -> Unit,
        onAccept: () -> Unit
    ) {
        BasicAlertDialog(onDismissRequest = onDismissRequest) {
            Card {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Nuke Database?",
                        fontSize = 32.sp
                    )
                    Text(
                        text = "This action will delete the entire database and is an irreversible action. Are you sure?"
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextButton(onClick = {
                            onAccept()
                            onDismissRequest()
                        }) { Text("Accept") }
                        TextButton(onClick = onDismissRequest) { Text("Cancel") }
                    }
                }
            }

        }
    }
}