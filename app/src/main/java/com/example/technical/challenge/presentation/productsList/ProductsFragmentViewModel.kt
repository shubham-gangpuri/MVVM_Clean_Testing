package com.example.technical.challenge.presentation.productsList

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.technical.challenge.R
import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.domain.model.network.request.SearchItemsModel
import com.example.technical.challenge.domain.usecase.ProductsListUseCase
import com.example.technical.challenge.utils.hasInternetConnection
import com.example.technical.challenge.utils.validateSearchInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsFragmentViewModel @Inject constructor(
    application: Application,
    private val productsListUseCase: ProductsListUseCase
) : AndroidViewModel(application){

    private var _productListResponse = MutableLiveData<List<SearchResults>>()
    val productListResponse: LiveData<List<SearchResults>> = _productListResponse

    var errorFieldVisibility = ObservableField(View.GONE)
    var errorFieldString = ObservableField<String>()
    var progressVisibility = ObservableField(View.GONE)
    var searchItems: SearchItemsModel = SearchItemsModel("","","")

    fun getProductsList() {
        val application = getApplication<Application>()
        if(!hasInternetConnection(application)) {
            errorFieldVisibility.set(View.VISIBLE)
            errorFieldString.set(application.getString(R.string.error_no_internet))
            return
        }

        // Validate the inputs
        validateSearchInput(application, searchItems.maker, searchItems.model, searchItems.year)?.let {
            errorFieldVisibility.set(View.VISIBLE)
            errorFieldString.set(it)
            _productListResponse.value = emptyList()
            return
        }
        progressVisibility.set(View.VISIBLE)
        viewModelScope.launch {
            when (val productListResponse = productsListUseCase.run(searchItems)) {
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
        progressVisibility.set(View.GONE)
        }
    }
}