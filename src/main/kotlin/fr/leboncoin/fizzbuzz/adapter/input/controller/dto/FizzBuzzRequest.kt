package fr.leboncoin.fizzbuzz.adapter.input.controller.dto

import fr.leboncoin.fizzbuzz.domain.FizzBuzz

data class FizzBuzzRequest(
    val int1: Int,
    val int2: Int,
    val limit: Int,
    val str1: String,
    val str2: String
) {
    fun toDomain() = FizzBuzz(
        int1 = int1,
        int2 = int2,
        limit = limit,
        str1 = str1,
        str2 = str2,
    )
}
