package com.example.scouting2025.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MatchData(

    @PrimaryKey
    val uid: Int,
    val onShift: String,

    // Prematch data
    val matchNumber: Int,
    val teamNumber: Int,

    // Auton data
    val autonLeave: Boolean,
    val autonCoralL1: Int,
    val autonCoralL2: Int,
    val autonCoralL3: Int,
    val autonCoralL4: Int,
    val autonAlgaeProcessor: Int,
    val autonAlgaeNet: Int,

    // Teleop data
    val teleopCoralL1: Int,
    val teleopCoralL2: Int,
    val teleopCoralL3: Int,
    val teleopCoralL4: Int,
    val teleopAlgaeProcessor: Int,
    val teleopAlgaeNet: Int,

    // Post match data
    val climbStatus: Any,
    // val penalties: Any
    val didRobotDisable: Boolean,
    val additionalNotes: String

)
