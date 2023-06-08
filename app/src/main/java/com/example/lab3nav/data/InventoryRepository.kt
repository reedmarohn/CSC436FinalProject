package com.example.lab3nav.data

import com.example.lab3nav.network.BarcodeAPI
import com.example.lab3nav.network.Item

interface InventoryRepository {
    suspend fun getBarcodeProducts(barcode : String) : Item
}

class DefaultInventoryRepository(
    private val apiService : BarcodeAPI
): InventoryRepository{
    override suspend fun getBarcodeProducts(barcode : String): Item {
        return apiService.getProducts(barcode)
    }
}