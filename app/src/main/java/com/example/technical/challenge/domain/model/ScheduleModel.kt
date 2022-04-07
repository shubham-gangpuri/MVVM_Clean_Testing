package com.example.technical.challenge.domain.model

import com.example.technical.challenge.data.network.response.remedies.Medicine
import com.example.technical.challenge.data.network.response.remedies.Schedule
import com.google.gson.annotations.SerializedName

data class ScheduleModel(
    @SerializedName("adherence_id"  ) var adherenceId  : String? = null,
    @SerializedName("patient_id"    ) var patientId    : String? = null,
    @SerializedName("remedy_id"     ) var remedyId     : String? = null,
    @SerializedName("alarm_time"    ) var alarmTime    : Long?    = null,
    @SerializedName("action"        ) var action       : String? = null,
    @SerializedName("action_time"   ) var actionTime   : String? = null,
    @SerializedName("dose_discrete" ) var doseDiscrete : String? = null,
    @SerializedName("dose_quantity" ) var doseQuantity : Long?    = null,
    @SerializedName("note"          ) var note         : String? = null,
    @SerializedName("date_created"  ) var dateCreated  : Long?              = null,
    @SerializedName("is_ongoing"    ) var isOngoing    : Boolean?          = null,
    @SerializedName("start_date"    ) var startDate    : Long?              = null,
    @SerializedName("medicine_id"   ) var medicineId   : String?           = null,
    @SerializedName("instruction"   ) var instruction  : String?           = null,
    @SerializedName("medicine_name" ) var medicineName : String?           = null,
    @SerializedName("medicine_type" ) var medicineType : String?           = null,
    @SerializedName("end_date"      ) var endDate      : Long?              = null,
    @SerializedName("repeat_cycle"  ) var repeatCycle  : Long?              = null,
    @SerializedName("allow_edit"    ) var allowEdit    : Boolean?          = null,
    @SerializedName("is_current"    ) var isCurrent    : Boolean?          = null,
    @SerializedName("medicine"      ) var medicine     : Medicine?         = Medicine(),
    @SerializedName("price"         ) var price        : Double?           = null
)
