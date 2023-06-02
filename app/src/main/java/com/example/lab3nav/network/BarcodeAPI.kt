package com.example.lab3nav.network
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
var barcode = ""
var apiKey = "9eqhwipfg1n6vy92hjbf98eyxd66u7"
var FORMAT_URL =
   "https://api.barcodelookup.com/v3/products?barcode=" + barcode +"&formatted=y&key=" + apiKey
var BASE_URL = "https://api.barcodelookup.com"

private val retrofit = Retrofit.Builder()
   .addConverterFactory(ScalarsConverterFactory.create())
   .baseUrl(BASE_URL)
   .build()


interface BarcodeAPI {
    @GET("photos")
    suspend fun getPhotos(): String
}

object ItemAPI {
    val retrofitService : BarcodeAPI by lazy {
       retrofit.create(BarcodeAPI::class.java)
    }
}