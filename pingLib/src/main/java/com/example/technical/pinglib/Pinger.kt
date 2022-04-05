package com.example.technical.pinglib

import org.apache.http.HttpException
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

/**
* Author : Shubham Gangpuri
* Date : 4 April 2022
*
* Pinger class is responsible for doing ping to an IP or server url
* */
object Pinger {

    /**
    * Author : Shubham Gangpuri
    * Date : 4 April 2022
    *
    * params :
    *   host (IP or Website url)
    *   port (port for which you want to ping)
    * Returns:
    *   Latency value wrapped in a sealed class with generic and network error exception handled
    * */
    internal fun doConnect(host: String?, port: Int?) : ResponseWrapper<Long> {
        return try {
            val time = System.currentTimeMillis()
            val socket = Socket()
            socket.connect(port?.let { InetSocketAddress(host, it) })
            socket.close()
            ResponseWrapper.Success(System.currentTimeMillis() - time)
        } catch ( throwable: Throwable) {
            when (throwable) {
                is IOException -> ResponseWrapper.NetworkError
                is HttpException -> {
                    ResponseWrapper.GenericError(404, throwable.message)
                }
                else -> {
                    ResponseWrapper.NetworkError
                }
            }
        }
    }

}