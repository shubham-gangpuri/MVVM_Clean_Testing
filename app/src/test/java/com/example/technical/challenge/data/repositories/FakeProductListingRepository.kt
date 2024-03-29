package com.example.technical.challenge.data.repositories

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import com.example.technical.challenge.domain.model.network.request.SearchItemsModel
import com.example.technical.challenge.domain.repositories.ProductListingRepository

class FakeProductListingRepository : ProductListingRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getProductList(
        searchItemsModel: SearchItemsModel
    ): ResultWrapper<ProductListResponse> {
        return if(shouldReturnNetworkError) {
            ResultWrapper.GenericError(503, null)
        } else {
            ResultWrapper.Success(ProductListResponse(emptyList()))
        }
    }

}