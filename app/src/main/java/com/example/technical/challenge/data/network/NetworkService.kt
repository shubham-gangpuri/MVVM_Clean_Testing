package com.example.technical.challenge.data.network

import com.example.technical.challenge.data.network.response.productlist.SearchResults
import retrofit2.http.GET

interface NetworkService {

    companion object {
        const val ENDPOINT = "https://gist.githubusercontent.com/"
    }

    @GET("anonymous/290132e587b77155eebe44630fcd12cb/raw/777e85227d0c1c16e466475bb438e0807900155c/sk_hosts")
    suspend fun getProductList(): List<SearchResults>

}