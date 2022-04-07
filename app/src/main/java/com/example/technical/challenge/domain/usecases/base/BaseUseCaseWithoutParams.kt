package com.example.technical.challenge.domain.usecases.base

interface BaseUseCaseWithoutParams< R > {
    suspend fun run() : R
}