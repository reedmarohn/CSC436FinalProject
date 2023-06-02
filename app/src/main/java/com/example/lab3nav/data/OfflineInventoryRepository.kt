package com.example.lab3nav.data

import kotlinx.coroutines.flow.Flow

class OfflineInventoryRepository(private val inventoryDao : InventoryDao) : InventoryDBRepository
    {
        override fun getAllItemsStream(): Flow<List<InventoryItem>> = inventoryDao.getAllItems()

        override fun getItemStream(id: Int): Flow<InventoryItem?> = inventoryDao.getItem(id)

        override suspend fun insertItem(item: InventoryItem) = inventoryDao.insert(item)

        override suspend fun deleteItem(item: InventoryItem) = inventoryDao.delete(item)

        override suspend fun updateItem(item: InventoryItem) = inventoryDao.update(item)
    }