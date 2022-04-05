package com.example.technical.pinglib

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Author : Shubham Gangpuri
 * Date : 3 April 2022
 *
 * params :
 *   context (context required for ConnectivityManager)
 * Returns:
 *   True or False (Whether internet is available or not)
 * */
fun isInternetConnected(context: Context): Boolean {
    var connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    )
    if(connectivityManager != null)
        connectivityManager = connectivityManager as ConnectivityManager
    else
        return true
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.activeNetworkInfo?.run {
            return when(type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                ConnectivityManager.TYPE_ETHERNET -> true
                else -> false
            }
        }
    }
    return false
}