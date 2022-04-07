package com.example.technical.challenge.domain.reprositories

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse

interface ProductListingRepository {
    suspend fun getProductList (make: String, model: String, year: String): ResultWrapper<ProductListResponse>
}