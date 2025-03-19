package com.example.scouting2025.screens.homeScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scouting2025.enums.Tablets

@OptIn(ExperimentalMaterial3Api::class)
object HomeComponents {

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
                        val password = "smoke alarm ping lmao"
                        onEnter(password == adminPassword)
                    }),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
        }
    }

    @Composable
    fun StartMatchButton(
        device: Tablets,
        onClick: () -> Unit
    ) {
        Button(
            modifier = Modifier
                .fillMaxHeight(),
            onClick = onClick,
            shape = MaterialTheme.shapes.extraLarge,
            contentPadding = PaddingValues(horizontal = 64.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = when (device) {
                    Tablets.B1, Tablets.B2, Tablets.B3 -> Color.Blue
                    Tablets.R1, Tablets.R2, Tablets.R3 -> Color.Red
                }
            )
        ) {
            Text(
                text = "Start recording match",
                fontSize = 32.sp
            )
        }
    }

}