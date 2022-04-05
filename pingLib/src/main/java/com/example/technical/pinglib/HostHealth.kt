package com.example.technical.pinglib

/**
* Author : Shubham Gangpuri
* Date : 5 April 2022
*
* Data class for containing all information about the host health.
* params :
*   host (IP or Website url)
*   error (List of generic errors captured by ping attempts)
*   networkError (List of network errors captured by ping attempts)
*   totalFailAttempts (Number of time ping got failed)
*   totalSuccess (Number of time successfully got connected)
*   avgLatency (Average time taken for successful communication)
* */
data class HostHealth(
    val host: String,
    val error: List<ResponseWrapper.GenericError>,
    val networkError: List<ResponseWrapper.NetworkError>,
    val totalFailAttempts: Int,
    val totalSuccess: Int,
    val avgLatency: Long
)
