package com.example.technical.challenge.data.network

import com.example.technical.challenge.data.network.response.adherences.AdherencesResponse
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import com.example.technical.challenge.data.network.response.remedies.RemediesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    companion object {
        const val ENDPOINT = "https://34574e81-855b-4c10-8987-935950fdd23c.mock.pstmn.io/"
    }

    @GET("search")
    suspend fun getProductList(@Query("make") make: String,
                               @Query("model") model: String,
                               @Query("year") year: String): ProductListResponse

    @GET("adherences")
    suspend fun getAdherences(): AdherencesResponse

    @GET("remedies")
    suspend fun getRemedies(): RemediesResponse

}