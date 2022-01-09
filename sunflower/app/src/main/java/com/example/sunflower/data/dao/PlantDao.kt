package com.example.sunflower.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sunflower.data.domain.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants(): Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId: String): Flow<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants:List<Plant>)
}