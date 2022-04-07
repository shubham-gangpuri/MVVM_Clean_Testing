package com.example.technical.challenge.domain.reprositories

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.domain.model.ScheduleModel

interface ScheduleListingRepository {
    suspend fun getScheduleList (): ResultWrapper<Map<String?, List<ScheduleModel?>>>
}