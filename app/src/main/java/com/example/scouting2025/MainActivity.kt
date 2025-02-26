package com.example.scouting2025

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scouting2025.database.AppDatabase
import com.example.scouting2025.database.buildAppDatabase
import com.example.scouting2025.navigation.NavScreen
import com.example.scouting2025.screens.homeScreen.HomeScreenViewModel
import com.example.scouting2025.ui.theme.Scouting2025Theme

const val CREATE_FILE = 1

class MainActivity : ComponentActivity() {

    private lateinit var appDatabase: AppDatabase
    private val homeScreenViewModel by viewModels<HomeScreenViewModel>()

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

                    composable<NavScreen> { /* TODO: Add screen composable here */ }
                    // TODO: Add other screens here...

                }

            }
        }
    }

    // Launch file picker to select database export destination
    private fun launchExportPathSelector() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/csv"
            // TODO: Update filename to be unique each time
            putExtra(Intent.EXTRA_TITLE, "database-export.csv")
        }
        @Suppress("DEPRECATION")
        startActivityForResult(intent, CREATE_FILE)
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

            CREATE_FILE -> data?.data.also { /* TODO: Call csv exporter function */ }

        }
    }

}