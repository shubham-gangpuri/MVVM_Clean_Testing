package com.example.technical.challenge.domain.base

interface BaseUseCaseWithParams< P, R > {
    suspend fun run(params : P) : R
}