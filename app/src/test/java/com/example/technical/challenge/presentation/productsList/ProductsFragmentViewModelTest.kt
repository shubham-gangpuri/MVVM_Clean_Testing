package com.example.technical.challenge.presentation.productsList

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.technical.challenge.R
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.data.repositories.FakeProductListingRepository
import com.example.technical.challenge.domain.usecases.product.ProductsListUseCaseImp
import com.example.technical.challenge.utils.MainCoroutineRule
import com.example.technical.challenge.utils.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductsFragmentViewModelTest{

    @Mock
    private lateinit var applicationMock: Application

    private lateinit var viewModel: ProductsFragmentViewModel
    private lateinit var fakeProductListingRepository: FakeProductListingRepository

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        applicationMock = mock(Application::class.java)
        fakeProductListingRepository = FakeProductListingRepository()
        viewModel = ProductsFragmentViewModel(applicationMock, ProductsListUseCaseImp(fakeProductListingRepository))
    }

    @Test
    fun `call getProductsList when fetch should return emptyList`(){
        runBlocking {
            viewModel.getProductsList()
            val value = viewModel.productListResponse.getOrAwaitValueTest()
            assertThat(value).isEqualTo(emptyList<SearchResults>())
        }
    }

    @Test
    fun `call getProductsList with shouldReturnNetworkError true when fetch should return GenericError`(){
        runBlocking {
            fakeProductListingRepository.setShouldReturnNetworkError(true)
            `when`(applicationMock.getString(R.string.error_generic)).thenReturn("Something went wrong.")
            viewModel.getProductsList()
            assertEquals("Something went wrong.", viewModel.errorFieldString.get())
        }
    }

}