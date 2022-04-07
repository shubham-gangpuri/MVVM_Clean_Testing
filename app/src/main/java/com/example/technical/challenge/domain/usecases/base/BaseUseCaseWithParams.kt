package com.example.technical.challenge.domain.usecases.base

interface BaseUseCaseWithParams< P, R > {
    suspend fun run(params : P) : R
}