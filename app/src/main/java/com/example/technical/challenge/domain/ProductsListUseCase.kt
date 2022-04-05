package com.example.technical.challenge.domain

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.domain.base.BaseUseCaseWithoutParams

interface ProductsListUseCase : BaseUseCaseWithoutParams<ResultWrapper<List<SearchResults>?>> {
    override suspend fun run(): ResultWrapper<List<SearchResults>?>
}