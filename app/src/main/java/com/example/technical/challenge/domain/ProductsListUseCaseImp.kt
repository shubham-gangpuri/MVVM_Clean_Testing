package com.example.technical.challenge.domain

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import javax.inject.Inject

class ProductsListUseCaseImp @Inject constructor(
    private val productListingRepository: ProductListingRepository
) : ProductsListUseCase {

    override suspend fun run(params: List<String>): ResultWrapper<ProductListResponse> {
        return productListingRepository.getProductList(params[0], params[1], params[2])
    }
}