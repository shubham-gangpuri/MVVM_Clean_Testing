package com.example.technical.challenge.data.repositories

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.base.SafeApiCaller
import com.example.technical.challenge.data.network.NetworkService
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import com.example.technical.challenge.domain.model.network.request.SearchItemsModel
import com.example.technical.challenge.domain.repositories.ProductListingRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultProductListingRepository @Inject constructor(private val service: NetworkService,
                                                          private val apiCaller: SafeApiCaller
// , Here we can inject DBService
): ProductListingRepository {

    override suspend fun getProductList (searchItemsModel: SearchItemsModel): ResultWrapper<ProductListResponse> =
        apiCaller.safeApiCall(Dispatchers.IO) {
            service.getProductList(searchItemsModel.maker, searchItemsModel.model, searchItemsModel.year)
        }
}