package com.example.technical.challenge.domain.usecases.schedule

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import com.example.technical.challenge.domain.model.ScheduleModel
import com.example.technical.challenge.domain.usecases.base.BaseUseCaseWithParams
import com.example.technical.challenge.domain.usecases.base.BaseUseCaseWithoutParams

interface ScheduleListUseCase :
    BaseUseCaseWithoutParams<ResultWrapper<Map<String?, List<ScheduleModel?>>>> {

    override suspend fun run(): ResultWrapper<Map<String?, List<ScheduleModel?>>>
}