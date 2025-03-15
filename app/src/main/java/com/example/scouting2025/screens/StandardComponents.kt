package com.example.scouting2025.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

@OptIn(ExperimentalMaterial3Api::class)
object StandardComponents {

    /**
     * Quick replacement for the standard icon button to remove boilerplate code.
     */
    @Composable
    fun SimpleIconButton(
        icon: ImageVector,
        onClick: () -> Unit
    ) {
        IconButton(onClick = onClick) { Icon(icon, null) }
    }

    /**
     * Standard top bar that allows for a title and navigation icon, typically a back button.
     */
    @Composable
    fun TopBar(
        title: String,
        icon: ImageVector = Icons.AutoMirrored.Default.ArrowBack,
        onBack: () -> Unit
    ) {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = { SimpleIconButton(icon, onBack) }
        )
    }

    /**
     * Standard extended floating action button used in the match recording screens. Takes a title,
     * icon, and onClick arguments.
     */
    @Composable
    fun ContinueButton(
        title: String,
        icon: ImageVector = Icons.AutoMirrored.Default.ArrowForward,
        onContinue: () -> Unit
    ) {
        ExtendedFloatingActionButton(
            text = { Text(title, style = MaterialTheme.typography.headlineLarge) },
            icon = { Icon(icon, null) },
            onClick = onContinue,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height(64.dp)
        )
    }

    /**
     * Standard text field that only accepts numbers. Accepts a modifier
     */
    @Composable
    fun NumberTextField(
        text: String,
        onTextChange: (String) -> Unit,
        label: String,
        modifier: Modifier = Modifier,
        leadingIcon: ImageVector? = null,
        isError: Boolean = false,
        onErrorText: String = ""
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { newText: String ->
                // Only call the given onTextChange lambda when the newText is a number AND if
                // the length of the text is less than 7 characters because converting to an int
                // later when its bigger causes an error.
                // This clamps the acceptable text to ONLY digits.
                if (newText.length < 7 && newText.isDigitsOnly()) {
                    onTextChange(newText)
                }
            },
            label = { Text(label) },
            // The argument for the leading icon in the outlined text field is nullable so if we do
            // not provide an argument, our function's default is to set it to null and do nothing.
            // If we provide an icon, then we turn that into an icon composable.
            leadingIcon = if (leadingIcon == null) {
                leadingIcon
            } else {
                { Icon(leadingIcon, null) }
            },
            // If there is an error (state given from the isError argument), then the text field
            // turns red and displays the onErrorText under the text box.
            isError = isError,
            supportingText = {
                if (isError) { Text(onErrorText) }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = modifier
        )
    }

    /**
     * Standard check box
     */
    @Composable
    fun LabeledCheckBox(
        state: Boolean,
        onStateChange: (Boolean) -> Unit,
        label: String,
        modifier: Modifier = Modifier,
        textStyle: TextStyle = MaterialTheme.typography.bodyLarge
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { onStateChange(!state) }
        ) {

            Checkbox(
                checked = state,
                onCheckedChange = onStateChange
            )

            Text(
                text = label,
                style = textStyle,
                softWrap = true,
                modifier = Modifier
                    .padding(end = 16.dp)
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
}