package com.example.technical.pinglib

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

/**
 * Author : Shubham Gangpuri
 * Date : 2 April 2022
 *
 * PingStream class is responsible for checking the health of an IP or server url
 * params :
 *  builder (Builder class instance as PingStream is using builder pattern for creating its object)
 *  context (context required for ConnectivityManager)
 *  host (IP or Website url)
 * */
class PingStream private constructor(builder: Builder, private val context: Context, private val host: String) {

    private val port: Int
    private val pings: Int
    private val doCaching: Boolean

    companion object{
        private val websiteLatencyMapping = mutableMapOf<String, HostHealth>()
    }

    init {
        this.port = builder.port
        this.pings = builder.pings
        this.doCaching = builder.doCaching
    }

    /**
     * Author : Shubham Gangpuri
     * Date : 2 April 2022
     *
     * Returns:
     *   HostHealth wrapped in a sealed class with generic and network error exception handled
     * */
    suspend fun getHostHealth(): ResponseWrapper<HostHealth>{
        return if(doCaching && websiteLatencyMapping.isNotEmpty() && websiteLatencyMapping.containsKey(host)){
            val value : HostHealth? = websiteLatencyMapping[host]
            value?.let {
                ResponseWrapper.Success(it)
            }?:ResponseWrapper.GenericError(404, context.getString(R.string.error_generic))
        } else if(isInternetConnected(context)) {
            val hostHealth = getAvgLatency()
            websiteLatencyMapping[host] = hostHealth
            ResponseWrapper.Success(hostHealth)
        }else
            ResponseWrapper.NetworkError
    }

    /**
     * Author : Shubham Gangpuri
     * Date : 2 April 2022
     *
     * Returns:
     *   HostHealth with full information about the host, Avg latency, errors
     * */
    private suspend fun getAvgLatency(): HostHealth{
        var totalLatency = 0L
        var totalSuccess = 0
        var avgLatency = -1L
        val error: MutableList<ResponseWrapper.GenericError> = mutableListOf()
        val networkError: MutableList<ResponseWrapper.NetworkError> = mutableListOf()
        val jobs = arrayListOf<Deferred<ResponseWrapper<Long>>>()
        (1..pings).forEach { _ ->
            jobs += CoroutineScope(Dispatchers.IO).async {
                Pinger.doConnect(host, port)
            }
        }
        jobs.forEach {
            it.await().let { latency ->
                when(latency) {
                    is ResponseWrapper.Success -> {
                        totalSuccess += 1
                        totalLatency += latency.value
                    }
                    is ResponseWrapper.GenericError -> {
                        error.add(latency)
                    }
                    is ResponseWrapper.NetworkError -> {
                        networkError.add(latency)
                    }
                }
            }
        }

        if(totalLatency > 0)
            avgLatency = totalLatency/totalSuccess

        return HostHealth(host, error, networkError,pings - totalSuccess, totalSuccess, avgLatency)
    }

    /**
     * Author : Shubham Gangpuri
     * Date : 2 April 2022
     *
     * Builder class is responsible to provide the PingStream class object
     * params :
     *  context (context required for ConnectivityManager)
     *  host (IP or Website url)
     * */
    class Builder(private val context: Context, private val host: String) {

        // optional parameter for port on which IP or URL service running
        var port: Int = 80

        // optional parameter for number of ping, one wants to connect with an IP or URL
        var pings: Int = 5

        // optional parameter for caching
        var doCaching: Boolean = false

        fun port(port: Int) = apply { this.port = port }
        fun pings(pings: Int) = apply { this.pings = pings }
        fun doCaching(caching: Boolean) = apply { this.doCaching = caching }
        fun build() = PingStream(this, context, host)
    }
}