package com.example.technical.challenge.utils

import android.content.Context
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidationUtilsKtTest{

    @Mock
    lateinit var context: Context

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        context = mock(Context::class.java)
        doReturn("Error")
            .`when`(context)
            .getString(any(Int::class.java))
    }

    @Test
    fun `empty maker return an error`(){
        val result = validateSearchInput(
            context,
            "",
            "Juke",
            "1989"
        )
        assertThat(result).isNotNull()
    }

    @Test
    fun `empty model return an error`(){
        val result = validateSearchInput(
            context,
            "Nissan",
            "",
            "1989"
        )
        assertThat(result).isNotNull()
    }

    @Test
    fun `empty year return an error`(){
        val result = validateSearchInput(
            context,
            "Nisaan",
            "Juke",
            ""
        )
        assertThat(result).isNotNull()
    }

    @Test
    fun `maker length less than 2 character return an error`(){
        val result = validateSearchInput(
            context,
            "N",
            "Juke",
            "1989"
        )
        assertThat(result).isNotNull()
    }

    @Test
    fun `maker length greater than 40 character return an error`(){
        val result = validateSearchInput(
            context,
            "Nissan company ptv limited Leyton London United Kingdom E102QS",
            "Juke",
            "1989"
        )
        assertThat(result).isNotNull()
    }

    @Test
    fun `year should be greater than current year return error`(){
        val result = validateSearchInput(
            context,
            "Nissan",
            "Juke",
            "2023"
        )
        assertThat(result).isNotNull()
    }

}