package com.example.technical.challenge.data.mapper

interface MergeMapper<A: Any, B: Any, T: Any> {
    fun merge(a:A, b:B): T
}