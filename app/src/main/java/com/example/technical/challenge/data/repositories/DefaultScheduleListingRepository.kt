package com.example.technical.challenge.data.repositories

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.base.SafeApiCaller
import com.example.technical.challenge.data.mapper.MergeMapper
import com.example.technical.challenge.data.network.NetworkService
import com.example.technical.challenge.data.network.response.adherences.Adherence
import com.example.technical.challenge.data.network.response.remedies.Remedy
import com.example.technical.challenge.domain.model.ScheduleModel
import com.example.technical.challenge.domain.reprositories.ScheduleListingRepository
import com.example.technical.challenge.utils.getDateTime
import kotlinx.coroutines.Dispatchers
import java.util.*
import javax.inject.Inject

class DefaultScheduleListingRepository @Inject constructor(private val service: NetworkService,
                                                           private val apiCaller: SafeApiCaller
// , Here we can inject DBService
): ScheduleListingRepository, MergeMapper<List<Remedy>,List<Adherence>,Map<String?, List<ScheduleModel?>>> {

    override suspend fun getScheduleList(): ResultWrapper<Map<String?, List<ScheduleModel?>>> {
        val adherencesResponse = apiCaller.safeApiCall(Dispatchers.IO) {
            service.getAdherences()
        }
        val remediesResponse = apiCaller.safeApiCall(Dispatchers.IO) {
            service.getRemedies()
        }

        var adherenceList = emptyList<Adherence>()
        when (adherencesResponse){
            is ResultWrapper.Success -> {
                adherenceList = adherencesResponse.value.data
            }
            is ResultWrapper.NetworkError -> {
                return adherencesResponse
            }
            is ResultWrapper.GenericError -> {
                return adherencesResponse
            }
        }

        var remediesList = emptyList<Remedy>()
        when (remediesResponse){
            is ResultWrapper.Success -> {
                remediesList = remediesResponse.value.data
            }
            is ResultWrapper.NetworkError -> {
                return remediesResponse
            }
            is ResultWrapper.GenericError -> {
                return remediesResponse
            }
        }

        return ResultWrapper.Success(merge(remediesList, adherenceList))

    }

    override fun merge(remedyList: List<Remedy>, adherenceList: List<Adherence>): Map<String?, List<ScheduleModel?>> {
        val remedyById: Map<String?, Remedy> = remedyList.associateBy { it.remedyId }

        val result = adherenceList.filter { remedyById[it.remedyId] != null }.map {  adherence ->
            remedyById[adherence.remedyId]?.let { remedy ->
                ScheduleModel(
                    adherenceId = adherence.adherenceId,
                    patientId = adherence.patientId,
                    remedyId = adherence.remedyId,
                    alarmTime = adherence.alarmTime,
                    action = adherence.action,
                    actionTime = adherence.actionTime,
                    doseDiscrete = adherence.doseDiscrete,
                    doseQuantity = adherence.doseQuantity,
                    note = adherence.note,
                    dateCreated = remedy.dateCreated,
                    isOngoing = remedy.isOngoing,
                    startDate = remedy.startDate,
                    medicineId = remedy.medicineId,
                    instruction = remedy.instruction,
                    medicineName = remedy.medicineName,
                    medicineType = remedy.medicineType,
                    endDate = remedy.endDate,
                    repeatCycle = remedy.repeatCycle,
                    allowEdit = remedy.allowEdit,
                    isCurrent = remedy.isCurrent,
                    medicine = remedy.medicine,
                    price = remedy.price
                )
            }
        }

        val remedyByDate: Map<String?, List<ScheduleModel?>> = result.groupBy { it?.alarmTime?.let { alarmTime ->
            getDateTime(
                alarmTime
            )
        } }

        return remedyByDate
    }
}