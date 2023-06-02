package com.example.lab3nav.data

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Inventory")

data class InventoryItem(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var productName: String = "",
    @StringRes val productCategory: Int = 0,
    var expirationDate: String = "",
    var quantity: Int = 0
)
