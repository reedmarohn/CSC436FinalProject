package com.example.lab3nav.data

import androidx.annotation.StringRes

data class InventoryUiState(
    val id : Int = 0,
    var productName: String = "",
    @StringRes val productCategory: Int = 0,
    var expirationDate: String = "",
    var quantity: Int = 0,
    var Barcode: String = ""
)
