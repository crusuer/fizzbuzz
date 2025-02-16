package fr.leboncoin.fizzbuzz.adapter.input.controller

import fr.leboncoin.fizzbuzz.adapter.input.controller.dto.FizzBuzzRequest
import fr.leboncoin.fizzbuzz.adapter.input.controller.dto.FizzBuzzResponse
import fr.leboncoin.fizzbuzz.application.FizzBuzzUseCase
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/fizzbuzz")
class FizzBuzzController(private val useCase: FizzBuzzUseCase) {
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun doFizzBuzz(request: FizzBuzzRequest): ResponseEntity<FizzBuzzResponse> {
        if (request.int1 < 1 || request.int2 < 1 || request.limit < 1) {
            throw IllegalArgumentException("int1, int2 and limit should be greater than 1")
        }
        val result = useCase.execute(request.toDomain())

        return ResponseEntity.ok(FizzBuzzResponse(result))
    }
}
