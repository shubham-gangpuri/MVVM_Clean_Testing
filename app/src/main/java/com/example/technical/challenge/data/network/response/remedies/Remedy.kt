package com.example.technical.challenge.data.network.response.remedies

import com.google.gson.annotations.SerializedName

data class Remedy(
    @SerializedName("_id"           ) var Id           : String?           = null,
    @SerializedName("remedy_id"     ) var remedyId     : String?           = null,
    @SerializedName("patient_id"    ) var patientId    : String?           = null,
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
