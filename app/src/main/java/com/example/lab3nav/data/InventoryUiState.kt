package com.example.lab3nav.data

data class InventoryUiState(
    val id : Int = 0,
    var productName: String = "",
    var imageURL: String = "",
    var productCategory: String = "",
    var expirationDate: String = "",
    var quantity: Int = 0,
    var Barcode: String = ""
)
