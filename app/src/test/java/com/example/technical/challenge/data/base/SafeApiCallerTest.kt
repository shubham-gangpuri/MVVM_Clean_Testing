package com.example.technical.challenge.data.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.io.IOException

@ExperimentalCoroutinesApi
class SafeApiCallerTest {

    private val dispatcher = TestCoroutineDispatcher()
    @Mock
    private lateinit var apiCaller: SafeApiCaller

    @Before
    fun doSetup(){
        MockitoAnnotations.initMocks(this)
        apiCaller = mock(SafeApiCaller::class.java)
    }

    @Test
    fun `when lambda returns successfully then it should emit the result as success`() {
        runBlockingTest {
            val lambdaResult = true
            val result = apiCaller.safeApiCall(dispatcher) { lambdaResult }
            assertEquals(ResultWrapper.Success(lambdaResult), result)
        }
    }

    @Test
    fun `when lambda throws IOException then it should emit the result as NetworkError`() {
        runBlockingTest {
            val result = apiCaller.safeApiCall(dispatcher) { throw IOException() }
            assertEquals(ResultWrapper.NetworkError, result)
        }
    }

//    @Test
//    fun `when lambda throws HttpException then it should emit the result as GenericError`() {
//        val errorBody = ResponseBody.create(MediaType.parse("text/json"),"Unexpected parameter")
//
//        runBlockingTest {
//            val result = apiCaller.safeApiCall(dispatcher) {
//                throw HttpException(Response.error<Any>(422, errorBody))
//            }
//            assertEquals(ResultWrapper.GenericError(422, ErrorResponse("Unexpected parameter")), result)
//        }
//    }

    @Test
    fun `when lambda throws unknown exception then it should emit GenericError`() {
        runBlockingTest {
            val result = apiCaller.safeApiCall(dispatcher) {
                throw IllegalStateException()
            }
            assertEquals(ResultWrapper.GenericError(), result)
        }
    }
}