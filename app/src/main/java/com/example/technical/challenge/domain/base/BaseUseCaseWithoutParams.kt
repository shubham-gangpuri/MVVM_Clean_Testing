package com.example.technical.challenge.domain.base

interface BaseUseCaseWithoutParams<R> {
    suspend fun run() : R
}