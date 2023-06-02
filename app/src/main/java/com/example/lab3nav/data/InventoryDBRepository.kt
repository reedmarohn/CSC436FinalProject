package com.example.lab3nav.data

import kotlinx.coroutines.flow.Flow

interface InventoryDBRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<InventoryItem>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<InventoryItem?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: InventoryItem)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: InventoryItem)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: InventoryItem)
}