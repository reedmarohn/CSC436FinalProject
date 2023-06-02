package com.example.lab3nav.data

interface AppContainer{
    val InventoryRepository : InventoryDBRepository
}

override val InventoryRepository : InventoryDBRepository by lazy{
    OfflineInventoryRepository(InventoryDatabase.getDatabase(context).itemDao())
}