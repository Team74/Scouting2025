package com.example.scouting2025.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

object ScreenComponents {
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