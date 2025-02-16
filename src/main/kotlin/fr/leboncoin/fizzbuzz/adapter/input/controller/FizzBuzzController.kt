package fr.leboncoin.fizzbuzz.adapter.input.controller

import fr.leboncoin.fizzbuzz.adapter.input.controller.dto.FizzBuzzRequest
import fr.leboncoin.fizzbuzz.adapter.input.controller.dto.FizzBuzzResponse
import fr.leboncoin.fizzbuzz.adapter.input.controller.dto.FizzBuzzStatisticsResponse
import fr.leboncoin.fizzbuzz.application.FizzBuzzUseCase
import fr.leboncoin.fizzbuzz.domain.FizzBuzz
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/fizzbuzz")
class FizzBuzzController(private val useCase: FizzBuzzUseCase) {
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun doFizzBuzz(request: FizzBuzzRequest): ResponseEntity<FizzBuzzResponse> {
        require(request.int1 > 1 && request.int2 > 1 && request.limit > 1) {
            "int1, int2 and limit should be greater than 1"
        }
        val result = useCase.execute(request.toDomain())

        return ResponseEntity.ok(FizzBuzzResponse(result))
    }

    @GetMapping(path = ["/statistics"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fetchStatistics(): ResponseEntity<FizzBuzzStatisticsResponse> {
        val mostFrequent = useCase.getMostFrequentRequest()
        return mostFrequent
            ?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.noContent().build()
    }

    private fun Pair<FizzBuzz, Int>.toResponse() = FizzBuzzStatisticsResponse(
        int1 = first.int1,
        int2 = first.int2,
        limit = first.limit,
        str1 = first.str1,
        str2 = first.str2,
        hits = second
    )
}
