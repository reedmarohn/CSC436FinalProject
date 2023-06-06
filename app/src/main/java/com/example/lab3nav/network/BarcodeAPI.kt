package com.example.lab3nav.network
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

//Json.nonstrict.parse()
//or
//.addConverterFactory(
//    Json(
//        JsonConfiguration(strictMode = false)
//    ).asConverterFactory(MediaType.get("application/json"))



//BarcodeAPI = marsapiservice and ItemApi = marsAPI
interface BarcodeAPI {
    //curly brackets indicate a variable name... need a way to pass in the barcode
    @GET("/v3/products?barcode={barcode}&formatted=y&key={apiKey}")
    suspend fun getProducts(): Products
}

