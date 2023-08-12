package com.example.technical.challenge.domain.usecase

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import com.example.technical.challenge.domain.base.BaseUseCaseWithParams
import com.example.technical.challenge.domain.model.network.request.SearchItemsModel

interface ProductsListUseCase :
    BaseUseCaseWithParams<SearchItemsModel, ResultWrapper<ProductListResponse?>> {
    override suspend fun run(searchItemsModel: SearchItemsModel): ResultWrapper<ProductListResponse>
}