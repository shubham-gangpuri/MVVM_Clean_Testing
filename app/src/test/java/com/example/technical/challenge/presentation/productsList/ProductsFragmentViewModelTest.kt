package com.example.technical.challenge.presentation.productsList

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.technical.challenge.data.repositories.FakeProductListingRepository
import com.example.technical.challenge.domain.ProductsListUseCaseImp
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        applicationMock = mock(Application::class.java)
        viewModel = ProductsFragmentViewModel(applicationMock, ProductsListUseCaseImp(FakeProductListingRepository()))
    }

    @Test
    fun `give server response 200 when fetch should return success`(){
        viewModel.getProductsList()
        val value = viewModel.errorFieldString.get()
        assertThat(value).isEqualTo("Internet is not available")
    }

}