package com.example.technical.challenge.presentation.productsList

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.domain.ProductsListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductsFragmentViewModelTest{

    @Mock
    private lateinit var applicationMock: Application
    @Mock
    private lateinit var productsListUseCaseMock: ProductsListUseCase

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiProductsObserver: Observer<List<SearchResults>>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        applicationMock = mock(Application::class.java)
        productsListUseCaseMock = mock(ProductsListUseCase::class.java)
    }

    @Test
    fun `give server response 200 when fetch should return success`(){
        runBlockingTest {
            doReturn(emptyList<SearchResults>())
                .`when`(productsListUseCaseMock)
                .run(listOf("","",""))
            val viewModel = ProductsFragmentViewModel(applicationMock, productsListUseCaseMock)
            viewModel.productListResponse.observeForever(apiProductsObserver)
            verify(productsListUseCaseMock).run(listOf("","",""))
            verify(apiProductsObserver).onChanged(emptyList())
            viewModel.productListResponse.removeObserver(apiProductsObserver)
        }
    }

}