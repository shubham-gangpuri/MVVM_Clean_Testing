package com.example.technical.challenge.data.repositories

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.domain.ProductListingRepository

class FakeProductListingRepository : ProductListingRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getProductList(
    ): ResultWrapper<List<SearchResults>> {
        return if(shouldReturnNetworkError) {
            ResultWrapper.GenericError(503, null)
        } else {
            ResultWrapper.Success(emptyList())
        }
    }

}