package com.example.technical.challenge.domain.usecases.schedule

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import com.example.technical.challenge.domain.model.ScheduleModel
import com.example.technical.challenge.domain.reprositories.ScheduleListingRepository
import com.example.technical.challenge.domain.usecases.schedule.ScheduleListUseCase
import javax.inject.Inject

class ScheduleListUseCaseImp @Inject constructor(
    private val scheduleListingRepository: ScheduleListingRepository
) : ScheduleListUseCase {

    override suspend fun run(): ResultWrapper<Map<String?, List<ScheduleModel?>>> =
        scheduleListingRepository.getScheduleList()

}