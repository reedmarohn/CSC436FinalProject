package com.example.lab3nav.data

import com.example.lab3nav.network.BarcodeAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val inventoryRepository : InventoryRepository
  //  val inventoryDBRepository : InventoryDBRepository
}
class DefaultAppContainer : AppContainer{

    var BASE_URL = "https://api.barcodelookup.com"
    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()
    val retrofitService : BarcodeAPI by lazy {
       retrofit.create(BarcodeAPI::class.java)
    }

    override val inventoryRepository: InventoryRepository by lazy {
        DefaultInventoryRepository(retrofitService)
    }

//    override val inventoryDBRepository : InventoryDBRepository by lazy{
//        OfflineInventoryRepository(InventoryDatabase.getDatabase(context).itemDao())
//    }

}


