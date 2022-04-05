package com.example.technical.pinglib

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PingStreamTest{

    @Mock
    private lateinit var context: Context

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun doSetup(){
        MockitoAnnotations.initMocks(this)
        context = mock(Context::class.java)
    }

    @Test
    fun `if host is correct then return Success`(){
        val pingStream = PingStream.Builder(context, "www.google.com").pings(5).doCaching(true)
            .port(80).build()
        runBlocking {
            val result = pingStream.getHostHealth()
            assertThat((result as ResponseWrapper.Success).value.avgLatency).isNotEqualTo(-1L)
        }
    }

    @Test
    fun `if caching is true then return same value every time`(){
        val pingStream = PingStream.Builder(context, "www.google.com").pings(5).doCaching(true)
            .port(80).build()
        val result = runBlocking {
            pingStream.getHostHealth()
        }
        runBlocking {
            delay(2000)
            val cachedResult = pingStream.getHostHealth()
            assertThat((cachedResult as ResponseWrapper.Success).value.avgLatency).isEqualTo((result as ResponseWrapper.Success).value.avgLatency)
        }
    }

}

