package com.example.lab3nav.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item : InventoryItem)

    @Update
    suspend fun update(item : InventoryItem)

    @Delete
    suspend fun delete(item : InventoryItem)

    @Query("SELECT * from Inventory WHERE id = :id")
    fun getItem(id: Int): Flow<InventoryItem>

    @Query("SELECT * from Inventory ORDER BY productName ASC")
    fun getAllItems(): Flow<List<InventoryItem>>
}