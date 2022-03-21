package com.example.technical.challenge.presentation.productsList

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.technical.challenge.R
import com.example.technical.challenge.TechnicalApplication
import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.domain.ProductsListUseCase
import com.example.technical.challenge.utils.hasInternetConnection
import com.example.technical.challenge.utils.validateSearchInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsFragmentViewModel @Inject constructor(
    application: Application,
    private val productsListUseCase: ProductsListUseCase) : AndroidViewModel(application){

    private var _productListResponse = MutableLiveData<List<SearchResults>>()
    val productListResponse: LiveData<List<SearchResults>> = _productListResponse

    var maker = ObservableField("")
    var model = ObservableField("")
    var year = ObservableField("")
    var errorFieldVisibility = ObservableField(View.GONE)
    var errorFieldString = ObservableField<String>()

    fun getProductsList() {
        val application = getApplication<TechnicalApplication>()
        if(!hasInternetConnection(application)) {
            errorFieldVisibility.set(View.VISIBLE)
            errorFieldString.set(application.getString(R.string.error_no_internet))
            return
        }

        // Validate the inputs
        validateSearchInput(application, maker.get()!!, model.get()!!, year.get()!!)?.let {
            errorFieldVisibility.set(View.VISIBLE)
            errorFieldString.set(it)
            _productListResponse.value = emptyList()
            return
        }

        viewModelScope.launch {
            when (val productListResponse = productsListUseCase.run(listOf(maker.get()!!, model.get()!!, year.get()!!))) {
                is ResultWrapper.NetworkError -> {
                    errorFieldVisibility.set(View.VISIBLE)
                    errorFieldString.set(application.getString(R.string.error_zero_record))
                }
                is ResultWrapper.GenericError -> {
                    errorFieldVisibility.set(View.VISIBLE)
                    errorFieldString.set(application.getString(R.string.error_generic))
                }
                is ResultWrapper.Success -> {
                    errorFieldVisibility.set(View.GONE)
                    _productListResponse.postValue(productListResponse.value.searchResults)
                }
            }
        }
    }
}