package com.example.lab3nav.network
import retrofit2.http.GET
import retrofit2.http.Path

//Json.nonstrict.parse()
//or
//.addConverterFactory(
//    Json(
//        JsonConfiguration(strictMode = false)
//    ).asConverterFactory(MediaType.get("application/json"))


//9eqhwipfg1n6vy92hjbf98eyxd66u7

//BarcodeAPI = marsapiservice and ItemApi = marsAPI
interface BarcodeAPI {
    //curly brackets indicate a variable name... need a way to pass in the barcode
    @GET("/v3/products?barcode={barcode}&formatted=y&key=9eqhwipfg1n6vy92hjbf98eyxd66u7")
    suspend fun getProducts(@Path("barcode") barcode : String): Products
}

