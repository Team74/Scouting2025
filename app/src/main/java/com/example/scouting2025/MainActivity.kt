package com.example.scouting2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts.CreateDocument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.scouting2025.database.AppDatabase
import com.example.scouting2025.database.DeviceDataStore
import com.example.scouting2025.database.buildAppDatabase
import com.example.scouting2025.database.exportDatabaseToCSV
import com.example.scouting2025.screens.MatchDataNavType
import com.example.scouting2025.screens.NavScreen
import com.example.scouting2025.screens.adminScreen.AdminScreen
import com.example.scouting2025.screens.autonScreen.AutonScreen
import com.example.scouting2025.screens.homeScreen.HomeScreen
import com.example.scouting2025.screens.matchListScreen.MatchListScreen
import com.example.scouting2025.screens.postMatchScreen.PostMatchScreen
import com.example.scouting2025.screens.preMatchScreen.PreMatchScreen
import com.example.scouting2025.screens.revisionScreen.RevisionScreen
import com.example.scouting2025.screens.teleopScreen.TeleopScreen
import com.example.scouting2025.ui.theme.Scouting2025Theme
import java.util.Calendar

class MainActivity : ComponentActivity() {

    // App database instance instantiated in the onCreate function
    private lateinit var appDatabase: AppDatabase
    // App settings instance instantiated in the onCreate function
    private lateinit var deviceDataStore: DeviceDataStore

    // Activity launcher for the file selector when exporting database to csv
    val getContent = registerForActivityResult(CreateDocument("text/csv")) { uri ->
        // If a proper uri was returned, export the database as a csv to that location
        if (uri != null) {
            exportDatabaseToCSV(uri, appDatabase, applicationContext.contentResolver)
        }
    }
    // Launch file picker to select database export destination
    private fun launchExportPathSelector(device: String) {
        val date = Calendar.getInstance()
        val month = date.get(Calendar.MONTH) + 1
        val day = date.get(Calendar.DAY_OF_MONTH)
        val year = date.get(Calendar.YEAR)
        getContent.launch("$device-$month.$day.$year-export.csv")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scouting2025Theme {

                // Instantiate database object
                appDatabase = buildAppDatabase(applicationContext)

                // Instantiate the preferences datastore
                deviceDataStore = DeviceDataStore(applicationContext)

                // Create the navController and screen composables
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavScreen.HomeScreen) {

                    /* ------------------------------------------------------------------------- */
                    // Start screen

                    composable<NavScreen.HomeScreen> { HomeScreen(deviceDataStore, navController) }

                    /* ------------------------------------------------------------------------- */
                    // Match recording screens

                    composable<NavScreen.PreMatchScreen>(MatchDataNavType.toTypeMap()) {
                        val matchData = it.toRoute<NavScreen.PreMatchScreen>().matchData
                        PreMatchScreen(deviceDataStore, navController, matchData)
                    }
                    composable<NavScreen.AutonScreen>(MatchDataNavType.toTypeMap()) {
                        val matchData = it.toRoute<NavScreen.AutonScreen>().matchData
                        AutonScreen(navController, matchData)
                    }
                    composable<NavScreen.TeleopScreen>(MatchDataNavType.toTypeMap()) {
                        val matchData = it.toRoute<NavScreen.TeleopScreen>().matchData
                        TeleopScreen(navController, matchData)
                    }
                    composable<NavScreen.PostMatchScreen>(MatchDataNavType.toTypeMap()) {
                        val matchData = it.toRoute<NavScreen.PostMatchScreen>().matchData
                        PostMatchScreen(appDatabase, navController, matchData)
                    }

                    /* ------------------------------------------------------------------------- */
                    // Match data revision screens for who's on shift

                    composable<NavScreen.MatchListScreen> {
                        MatchListScreen(appDatabase, navController)
                    }
                    composable<NavScreen.RevisionScreen> {
                        val uid = it.toRoute<NavScreen.RevisionScreen>().uid
                        RevisionScreen(appDatabase, navController, uid)
                    }

                    /* ------------------------------------------------------------------------- */
                    // Admin screen for unfiltered database access

                    composable<NavScreen.AdminScreen> {
                        AdminScreen(appDatabase, navController, deviceDataStore) { launchExportPathSelector(it) }
                    }

                }

            }
        }
    }

}