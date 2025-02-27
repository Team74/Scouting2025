package com.example.scouting2025.database

import android.content.ContentResolver
import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedWriter
import java.io.FileOutputStream

/**
 * Called when the launchExportPathSelector function finishes.
 * Creates a new thread that retrieves all match data and writes it to
 * the selected file path as a .csv file.
 */
fun exportDatabaseToCSV(
    uri: Uri?,
    appDatabase: AppDatabase,
    contentResolver: ContentResolver
) {

    // Null check the uri
    if (uri == null) return

    // Start an IO coroutine
    CoroutineScope(Dispatchers.IO).launch {

        // Get match data
        val allMatches = appDatabase.matchDataDao().getAll()

        // Inside a try-catch block...
        // Create the file at the uri destination
        try {
            // Create a buffered writer
            contentResolver.openFileDescriptor(uri, "w")?.use {
                FileOutputStream(it.fileDescriptor).bufferedWriter().use { out ->
                    // Write MatchData property names as headers
                    println("Writing headers")
                    out.writeln( MatchData.getFieldNamesInOne() )

                    // Write each MatchData instance's data as .csv rows
                    println("Writing data")
                    allMatches.forEach { match -> out.writeln(match.toString()) }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}

private fun BufferedWriter.writeln(str: String) {
    write(str + '\n')
}
