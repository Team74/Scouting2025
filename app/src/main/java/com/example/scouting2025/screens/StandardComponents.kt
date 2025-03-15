package com.example.scouting2025.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.scouting2025.screens.StandardComponents.IncrementingBox

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
        isAuton: Boolean = false
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Coral",
                fontSize = 24.sp
            )
            IncrementingBox(
                input = box1,
                label = "Level 4",
                isAuton = isAuton
            ) {onBox1Change(it)}
            IncrementingBox(
                input = box2,
                label = "Level 3",
                isAuton = isAuton
            ) {onBox2Change(it)}
            IncrementingBox(
                input = box3,
                label = "Level 2",
                isAuton = isAuton
            ) {onBox3Change(it)}
            IncrementingBox(
                input = box4,
                label = "Level 1",
                isAuton = isAuton
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
        isAuton: Boolean = false
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Algae",
                fontSize = 24.sp
            )
            IncrementingBox(
                input = box1,
                label = "Processor",
                isAuton = isAuton
            ) {onBox1Change(it)}
            IncrementingBox(
                input = box2,
                label = "Net",
                isAuton = isAuton
            ) {onBox2Change(it)}
        }
    }

    @Composable
    fun IncrementingBox (
        modifier: Modifier = Modifier,
        input: Int,
        label: String,
        isAuton: Boolean,
        onValueChange: (Int) -> Unit
    ) {

        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IncrementButton(
                increment = false,
                isAuton = isAuton,
                onClick = {
                    if (input > 0) onValueChange(input - 1)
                }
            )
            OutlinedTextField(
                value = input.toString(),
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .width(74.dp)
                    .height(64.dp)
            )
            IncrementButton(
                isAuton = isAuton,
                onClick = {
                    onValueChange(input + 1)
                }
            )
            Text(
                text = label
            )
        }
    }

    @Composable
    fun IncrementButton(
        increment: Boolean = true,
        isAuton: Boolean,
        onClick: () -> Unit
    ) {
        Button(
            onClick = onClick,
            contentPadding = PaddingValues(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = if(isAuton) ButtonDefaults.buttonColors(containerColor = Color.Green) else ButtonDefaults.buttonColors(),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .width(100.dp)
        ) {
            Text(
                text = if(increment) "+" else "-",
                fontSize = 48.sp
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun IncrementingBoxesPreview() {
//    IncrementingBox(input = 0, onValueChange = {}, label = "Test label")
//}