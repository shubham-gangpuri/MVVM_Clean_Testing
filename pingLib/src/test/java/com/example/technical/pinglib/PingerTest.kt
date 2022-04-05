package com.example.technical.pinglib

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PingerTest{

    @Test
    fun `if host is null return -1`(){
        val result = Pinger.doConnect(null, 80)
        assertThat(result).isEqualTo(ResponseWrapper.NetworkError)
    }

    @Test
    fun `if host is correct return value greater than -1`(){
        val result = Pinger.doConnect("www.google.com", 80)
        assertThat(result).isInstanceOf(ResponseWrapper.Success::class.java)
    }

    @Test
    fun `if port is not correct return value -1`(){
        val result = Pinger.doConnect("www.google.com", 8000)
        assertThat(result).isEqualTo(ResponseWrapper.NetworkError)
    }

}