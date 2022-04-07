package com.example.technical.challenge.data.network.response.remedies

import com.google.gson.annotations.SerializedName

data class RemediesResponse (
    @SerializedName("data" ) var data : ArrayList<Remedy> = arrayListOf()
)
