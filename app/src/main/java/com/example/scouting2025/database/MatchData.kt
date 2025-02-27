package com.example.scouting2025.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.scouting2025.enums.ClimbState

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
    val climbState: ClimbState,
    // val penalties: Any
    val didRobotDisable: Boolean,
    val additionalNotes: String

) {

    @Suppress("MemberVisibilityCanBePrivate")
    fun getFieldValues(): List<Any> {
        return listOf(
            uid,
            onShift,
            matchNumber,
            teamNumber,
            autonLeave,
            autonCoralL1,
            autonCoralL2,
            autonCoralL3,
            autonCoralL4,
            autonAlgaeProcessor,
            autonAlgaeNet,
            teleopCoralL1,
            teleopCoralL2,
            teleopCoralL3,
            teleopCoralL4,
            teleopAlgaeProcessor,
            teleopAlgaeNet,
            climbState,
            didRobotDisable,
            additionalNotes
        )
    }

    override fun toString(): String {
        return this
            .getFieldValues()
            .joinToString(", ") { field ->
                when (field) {
                    is String -> "\"$field\""
                    is Boolean -> if (field) "1" else "0"
                    else -> field.toString()
                }
            }
    }

    companion object {

        @Suppress("MemberVisibilityCanBePrivate")
        fun getFieldNames(): List<String> {
            return listOf(
                "uid",
                "onShift",
                "matchNumber",
                "teamNumber",
                "autonLeave",
                "autonCoralL1",
                "autonCoralL2",
                "autonCoralL3",
                "autonCoralL4",
                "autonAlgaeProcessor",
                "autonAlgaeNet",
                "teleopCoralL1",
                "teleopCoralL2",
                "teleopCoralL3",
                "teleopCoralL4",
                "teleopAlgaeProcessor",
                "teleopAlgaeNet",
                "climbState",
                "didRobotDisable",
                "additionalNotes"
            )
        }

        fun getFieldNamesInOne(): String {
            return getFieldNames()
                .joinToString(", ") { "\"$it\"" }
        }

    }

}
