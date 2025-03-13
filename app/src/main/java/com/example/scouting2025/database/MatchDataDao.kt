package com.example.scouting2025.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MatchDataDao {

    @Query("SELECT * FROM MatchData")
    suspend fun getAll(): List<MatchData>

    @Query("SELECT * FROM MatchData WHERE uid = :uid")
    suspend fun fetch(uid: Int): MatchData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(data: MatchData)

    @Delete
    suspend fun delete(data: MatchData)

    @Query("DELETE FROM MatchData")
    suspend fun nuke()

}