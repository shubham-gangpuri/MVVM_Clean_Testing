package com.example.technical.challenge.domain

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse

interface ProductsListUseCase : BaseUseCaseWithParams<List<String>, ResultWrapper<ProductListResponse?>>{
    override suspend fun run(params: List<String>): ResultWrapper<ProductListResponse>
}