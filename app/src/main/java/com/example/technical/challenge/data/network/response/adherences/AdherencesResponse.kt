package com.example.technical.challenge.data.network.response.adherences

import com.google.gson.annotations.SerializedName

data class AdherencesResponse(
    @SerializedName("data" ) var data : ArrayList<Adherence> = arrayListOf()
)
