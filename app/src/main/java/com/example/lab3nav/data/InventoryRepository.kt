package com.example.lab3nav.data

import com.example.lab3nav.network.BarcodeAPI
import com.example.lab3nav.network.Item
import com.example.lab3nav.network.Products

interface InventoryRepository {
    suspend fun getBarcodeProducts() : Products
}

class DefaultInventoryRepository(
    private val apiService : BarcodeAPI
): InventoryRepository{
    override suspend fun getBarcodeProducts(): Products {
        return apiService.getProducts()
    }
}