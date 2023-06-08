package com.example.lab3nav.network

import kotlinx.serialization.Serializable


@Serializable
data class Item(
    //for the parent object
    val products: List<Products>
)

@Serializable
data class Products (
   val category: String,
   //val images: List<String>,
    val title: String
)