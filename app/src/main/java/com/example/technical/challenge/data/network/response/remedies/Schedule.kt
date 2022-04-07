package com.example.technical.challenge.data.network.response.remedies

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("day_iterator" ) var dayIterator : Int?    = null,
    @SerializedName("alarm_window" ) var alarmWindow : Int?    = null,
    @SerializedName("day_offset"   ) var dayOffset   : String? = null,
    @SerializedName("dose_min"     ) var doseMin     : Int?    = null,
    @SerializedName("dose_max"     ) var doseMax     : Int?    = null
)
