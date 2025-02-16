package fr.leboncoin.fizzbuzz.adapter.input.controller

import fr.leboncoin.fizzbuzz.AbstractIntegrationTest
import fr.leboncoin.fizzbuzz.adapter.input.controller.dto.FizzBuzzRequest
import fr.leboncoin.fizzbuzz.adapter.input.controller.dto.FizzBuzzResponse
import fr.leboncoin.fizzbuzz.application.FizzBuzzUseCase
import io.restassured.RestAssured.given
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.reset
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus
import org.springframework.test.context.bean.override.mockito.MockitoBean

internal class FizzBuzzControllerIntegrationTest : AbstractIntegrationTest() {
    @MockitoBean
    private lateinit var useCase: FizzBuzzUseCase

    private val basePath = "/v1"

    @BeforeEach
    fun setUp() {
        reset(useCase)
    }

    @Test
    fun `should calculate fizz-buzz sequence`() {
        val request = FizzBuzzRequest(
            int1 = 2,
            int2 = 3,
            limit = 7,
            str1 = "ping",
            str2 = "pong"
        )
        val result = listOf("1", "ping", "pong", "ping", "5", "pingpong", "7")
        val expected = FizzBuzzResponse(result)

        `when`(useCase.execute(request.toDomain())).thenReturn(result)

        val response = given()
            .`when`()
            .params(
                mapOf(
                    "int1" to request.int1,
                    "int2" to request.int2,
                    "limit" to request.limit,
                    "str1" to request.str1,
                    "str2" to request.str2
                )
            )
            .post("$basePath/fizzbuzz")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .body()
            .`as`(FizzBuzzResponse::class.java)

        verify(useCase).execute(request.toDomain())
        assertThat(response).isEqualTo(expected)
    }

    @Test
    fun `should validate params and throw error`() {
        val request = FizzBuzzRequest(
            int1 = -1,
            int2 = 3,
            limit = 7,
            str1 = "ping",
            str2 = "pong"
        )

        given()
            .`when`()
            .params(
                mapOf(
                    "int1" to request.int1,
                    "int2" to request.int2,
                    "limit" to request.limit,
                    "str1" to request.str1,
                    "str2" to request.str2
                )
            )
            .post("$basePath/fizzbuzz")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
    }
}
