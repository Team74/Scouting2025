package com.example.scouting2025.screens.postMatchScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.scouting2025.enums.ClimbState

@OptIn(ExperimentalMaterial3Api::class)
object PostMatchComponents {

    @Composable
    fun ClimbStateDropDown(
        state: ClimbState,
        onStateChange: (ClimbState) -> Unit,
        expanded: Boolean,
        onExpandedChange: (Boolean) -> Unit
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = onExpandedChange
        ) {

            OutlinedTextField(
                readOnly = true,
                value = state.name,
                onValueChange = { /* DO NOTHING HERE */ },
                label = { Text("Climb State") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) }
            ) {
                ClimbState.entries.forEach { state: ClimbState ->
                    DropdownMenuItem(
                        text = { Text(state.name) },
                        onClick = {
                            onStateChange(state)
                            onExpandedChange(false)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }

        }
    }

    @Composable
    fun AdditionalNotesField(
        text: String,
        onTextChange: (String) -> Unit
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text("Additional Notes") },
            leadingIcon = { Icon(Icons.Default.ThumbUp, null) },
            singleLine = true
        )
    }
    
}