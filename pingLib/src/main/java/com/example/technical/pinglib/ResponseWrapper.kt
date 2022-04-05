package com.example.technical.pinglib

/**
 * Author : Shubham Gangpuri
 * Date : 3 April 2022
 *
 * A generic class that holds a value with its loading status.
 */
sealed class ResponseWrapper<out T>{
    data class Success<out T>(val value: T): ResponseWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null): ResponseWrapper<Nothing>()
    object NetworkError: ResponseWrapper<Nothing>()
}