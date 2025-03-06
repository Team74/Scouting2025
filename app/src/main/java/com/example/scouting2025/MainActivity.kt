package com.example.scouting2025

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.scouting2025.database.AppDatabase
import com.example.scouting2025.database.MatchData
import com.example.scouting2025.database.buildAppDatabase
import com.example.scouting2025.database.exportDatabaseToCSV
import com.example.scouting2025.enums.ActivityRequestCode
import com.example.scouting2025.screens.MatchDataNavType
import com.example.scouting2025.screens.NavScreen
import com.example.scouting2025.screens.adminScreen.AdminScreen
import com.example.scouting2025.screens.autonScreen.AutonScreen
import com.example.scouting2025.screens.homeScreen.HomeScreen
import com.example.scouting2025.screens.postmatchScreen.PostmatchScreen
import com.example.scouting2025.screens.prematchScreen.PrematchScreen
import com.example.scouting2025.screens.teleopScreen.TeleopScreen
import com.example.scouting2025.ui.theme.Scouting2025Theme
import java.util.Calendar
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {

    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scouting2025Theme {

                // Instantiate database object
                appDatabase = buildAppDatabase(applicationContext)

                // Create the navController and screen composables
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavScreen.HomeScreen) {

                    composable<NavScreen.HomeScreen> { HomeScreen(appDatabase, navController) }
                    composable<NavScreen.ShiftScreen> { /* TODO: Add screen composable here */ }
                    composable<NavScreen.RevisionScreen> { /* TODO: Add screen composable here */ }
                    composable<NavScreen.PrematchScreen>(
                        typeMap = mapOf(
                            typeOf<MatchData>() to MatchDataNavType.MatchDataType
                        )
                    ) {
                        val matchData = it.toRoute<NavScreen.PrematchScreen>().matchData
                        PrematchScreen(appDatabase, navController, matchData)
                    }
                    composable<NavScreen.AutonScreen> { AutonScreen(appDatabase, navController) }
                    composable<NavScreen.TeleopScreen> { TeleopScreen(appDatabase, navController) }
                    composable<NavScreen.PostmatchScreen> { PostmatchScreen(appDatabase, navController) }
                    composable<NavScreen.AdminScreen> {
                        AdminScreen(appDatabase, navController) { launchExportPathSelector() }
                    }

                }

            }
        }
    }

    // Launch file picker to select database export destination
    private fun launchExportPathSelector() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/csv"
            val date = Calendar.getInstance()
            val year = date.get(Calendar.YEAR)
            val month = date.get(Calendar.MONTH)
            val day = date.get(Calendar.DAY_OF_MONTH)
            putExtra(Intent.EXTRA_TITLE, "$year-$month-$day.database-export.csv")
        }
        @Suppress("DEPRECATION")
        startActivityForResult(intent, ActivityRequestCode.CREATE_FILE.ordinal)
    }

    // Called whenever an activity finishes
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        // If result was not ok, return early
        if (resultCode != RESULT_OK) return

        // Determine which request code was used for activity
        when (requestCode) {

            ActivityRequestCode.CREATE_FILE.ordinal -> data?.data.also {
                exportDatabaseToCSV(it, appDatabase, applicationContext.contentResolver)
            }

        }
    }

}