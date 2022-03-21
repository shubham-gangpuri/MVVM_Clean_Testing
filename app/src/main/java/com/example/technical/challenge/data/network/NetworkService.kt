package com.example.technical.challenge.data.network

import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    companion object {
        const val ENDPOINT = "http://mcuapi.mocklab.io/"
    }

    @GET("search")
    suspend fun getProductList(@Query("make") make: String,
                               @Query("model") model: String,
                               @Query("year") year: String): ProductListResponse

}