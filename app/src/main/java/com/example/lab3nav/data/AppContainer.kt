package com.example.lab3nav.data

import com.example.lab3nav.network.BarcodeAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val inventoryRepository : InventoryRepository
}
class DefaultAppContainer : AppContainer{
    val barcode = "077341125112"
    val apiKey = "&formatted=y&key=9eqhwipfg1n6vy92hjbf98eyxd66u7"

    var BASE_URL = "https://api.barcodelookup.com"
    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()
    val retrofitService : BarcodeAPI by lazy {
       retrofit.create(BarcodeAPI::class.java)
    }

    override val inventoryRepository: InventoryRepository by lazy {
        DefaultInventoryRepository(retrofitService)
    }


}
//override val InventoryRepository : InventoryDBRepository by lazy{
//    OfflineInventoryRepository(InventoryDatabase.getDatabase(context).itemDao())
//}

