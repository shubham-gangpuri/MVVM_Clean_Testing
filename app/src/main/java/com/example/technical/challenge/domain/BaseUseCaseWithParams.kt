package com.example.technical.challenge.domain

interface BaseUseCaseWithParams< P, R > {
    suspend fun run(params : P) : R
}