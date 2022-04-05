package com.example.technical.challenge.domain

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import com.example.technical.challenge.data.repositories.FakeProductListingRepository
import com.example.technical.challenge.domain.model.network.request.SearchItemsModel
import com.example.technical.challenge.domain.repositories.ProductListingRepository
import com.example.technical.challenge.domain.usecase.ProductsListUseCase
import com.example.technical.challenge.domain.usecase.ProductsListUseCaseImp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductsListUseCaseTest{

    private lateinit var productsListUseCase: ProductsListUseCase
    private lateinit var fakeProductListingRepository: ProductListingRepository

    @Before
    fun setup(){
        fakeProductListingRepository = FakeProductListingRepository()
        productsListUseCase = ProductsListUseCaseImp(fakeProductListingRepository)
    }

    @Test
    fun `no internet return generic error`(){
        (fakeProductListingRepository as FakeProductListingRepository).setShouldReturnNetworkError(true)
        runBlockingTest {
            val productListResponse = productsListUseCase.run(SearchItemsModel("","",""))
            assertEquals(ResultWrapper.GenericError(503, null), productListResponse)
        }
    }

    @Test
    fun `when lambda returns successfully then it should emit the empty list as success`(){
        runBlockingTest {
            val productListResponse = productsListUseCase.run(SearchItemsModel("","",""))
            assertEquals(ResultWrapper.Success(ProductListResponse(emptyList())), productListResponse)
        }
    }

}