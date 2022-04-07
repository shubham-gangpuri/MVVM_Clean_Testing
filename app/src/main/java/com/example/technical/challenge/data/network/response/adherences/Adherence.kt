package com.example.technical.challenge.data.network.response.adherences

import com.google.gson.annotations.SerializedName

data class Adherence(
    @SerializedName("_id"           ) var Id           : String? = null,
    @SerializedName("adherence_id"  ) var adherenceId  : String? = null,
    @SerializedName("patient_id"    ) var patientId    : String? = null,
    @SerializedName("remedy_id"     ) var remedyId     : String? = null,
    @SerializedName("alarm_time"    ) var alarmTime    : Long?    = null,
    @SerializedName("action"        ) var action       : String? = null,
    @SerializedName("action_time"   ) var actionTime   : String? = null,
    @SerializedName("dose_discrete" ) var doseDiscrete : String? = null,
    @SerializedName("dose_quantity" ) var doseQuantity : Long?    = null,
    @SerializedName("note"          ) var note         : String? = null
)
