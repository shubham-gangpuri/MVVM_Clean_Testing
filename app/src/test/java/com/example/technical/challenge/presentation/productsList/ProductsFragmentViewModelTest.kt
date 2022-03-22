package com.example.technical.challenge.presentation.productsList

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.data.repositories.FakeProductListingRepository
import com.example.technical.challenge.domain.ProductsListUseCaseImp
import com.example.technical.challenge.utils.MainCoroutineRule
import com.example.technical.challenge.utils.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductsFragmentViewModelTest{

    @Mock
    private lateinit var applicationMock: Application

    private lateinit var viewModel: ProductsFragmentViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        applicationMock = mock(Application::class.java)
        viewModel = ProductsFragmentViewModel(applicationMock, ProductsListUseCaseImp(FakeProductListingRepository()))
    }

    @Test
    fun `call getProductsList when fetch should return emptyList`(){
        runBlocking {
            viewModel.getProductsList()
            val value = viewModel.productListResponse.getOrAwaitValueTest()
            assertThat(value).isEqualTo(emptyList<SearchResults>())
        }
    }

}