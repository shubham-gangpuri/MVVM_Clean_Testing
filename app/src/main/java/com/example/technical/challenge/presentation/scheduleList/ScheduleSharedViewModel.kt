package com.example.technical.challenge.presentation.scheduleList

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.technical.challenge.R
import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.domain.model.ScheduleModel
import com.example.technical.challenge.domain.usecases.schedule.ScheduleListUseCase
import com.example.technical.challenge.utils.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleSharedViewModel @Inject constructor(
    application: Application,
    private val scheduleListUseCase: ScheduleListUseCase
) : AndroidViewModel(application){

    private var _scheduleListResponse = MutableLiveData<Map<String?, List<ScheduleModel?>>?>()
    val scheduleListResponse: LiveData<Map<String?, List<ScheduleModel?>>?> = _scheduleListResponse

    var errorFieldVisibility = ObservableField(View.GONE)
    var errorFieldString = ObservableField<String>()
    var progressVisibility = ObservableField(View.GONE)
    var isChanged: MutableLiveData<Int> = MutableLiveData(0)

    init {
        getScheduleList()
    }

    private fun getScheduleList() {
        val application = getApplication<Application>()
        if(!hasInternetConnection(application)) {
            errorFieldVisibility.set(View.VISIBLE)
            errorFieldString.set(application.getString(R.string.error_no_internet))
            return
        }

        progressVisibility.set(View.VISIBLE)
        viewModelScope.launch {
            when (val scheduleMap = scheduleListUseCase.run()) {
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
                    _scheduleListResponse.value = scheduleMap.value
                }
            }
        progressVisibility.set(View.GONE)
        }
    }
}