package com.example.technical.challenge.domain

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.SearchResults

interface ProductListingRepository {
    suspend fun getProductList (): ResultWrapper<List<SearchResults>>
}