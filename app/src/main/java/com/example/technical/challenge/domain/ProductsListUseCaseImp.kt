package com.example.technical.challenge.domain

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import javax.inject.Inject

class ProductsListUseCaseImp @Inject constructor(
    private val productListingRepository: ProductListingRepository
) : ProductsListUseCase {

    override suspend fun run(): ResultWrapper<List<SearchResults>> {
        return productListingRepository.getProductList()
    }
}