package com.example.scouting2025.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.scouting2025.enums.ClimbState
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class MatchData(

    @PrimaryKey
    val uid: Int = 0,
    val onShift: String = "",

    // Prematch data
    val matchNumber: String = "",
    val teamNumber: String = "",

    // Auton data
    val autonLeave: Boolean = false,
    val autonCoralL1: Int = 0,
    val autonCoralL2: Int = 0,
    val autonCoralL3: Int = 0,
    val autonCoralL4: Int = 0,
    val autonAlgaeProcessor: Int = 0,
    val autonAlgaeNet: Int = 0,

    // Teleop data
    val teleopCoralL1: Int = 0,
    val teleopCoralL2: Int = 0,
    val teleopCoralL3: Int = 0,
    val teleopCoralL4: Int = 0,
    val teleopAlgaeProcessor: Int = 0,
    val teleopAlgaeNet: Int = 0,

    // Post match data
    val climbState: ClimbState = ClimbState.DID_NOT_MAKE_IT,
    // val penalties: Any
    val didRobotDisable: Boolean = false,
    val additionalNotes: String = ""

) {

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
