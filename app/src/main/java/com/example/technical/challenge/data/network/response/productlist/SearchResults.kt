package com.example.technical.challenge.data.network.response.productlist

import com.google.gson.annotations.SerializedName

data class SearchResults(
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null,
    @SerializedName("icon" ) var icon : String? = null
)
