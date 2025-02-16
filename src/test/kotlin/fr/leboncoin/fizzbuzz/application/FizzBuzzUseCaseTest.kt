package fr.leboncoin.fizzbuzz.application

import fr.leboncoin.fizzbuzz.domain.FizzBuzz
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FizzBuzzUseCaseTest {
    private val underTest = FizzBuzzUseCase()
    private val str1 = "fizz"
    private val str2 = "buzz"

    @Test
    fun `should return regular fizz-buzz`() {
        val request = FizzBuzz(
            int1 = 3,
            int2 = 5,
            limit = 30,
            str1 = str1,
            str2 = str2
        )

        val expected = listOf(
            "1", "2", str1, "4", str2, str1, "7", "8", str1, str2, "11", str1, "13", "14", str1 + str2,
            "16", "17", str1, "19", str2, str1, "22", "23", str1, str2, "26", str1, "28", "29", str1 + str2
        )

        val result = underTest.execute(request)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should always return str1str2 for equal params`() {
        val request = FizzBuzz(
            int1 = 2,
            int2 = 2,
            limit = 7,
            str1 = str1,
            str2 = str2
        )

        val expected = listOf("1", str1 + str2, "3", str1 + str2, "5", str1 + str2, "7")

        val result = underTest.execute(request)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return regular sequence if multiples not exists`() {
        val request = FizzBuzz(
            int1 = 5,
            int2 = 6,
            limit = 4,
            str1 = str1,
            str2 = str2
        )

        val expected = listOf("1", "2", "3", "4")

        val result = underTest.execute(request)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return list containing only str1 if multiples of int2 not exists`() {
        val request = FizzBuzz(
            int1 = 4,
            int2 = 7,
            limit = 6,
            str1 = str1,
            str2 = str2
        )

        val expected = listOf("1", "2", "3", str1, "5", "6")

        val result = underTest.execute(request)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return list containing only str2 if multiples of int1 not exists`() {
        val request = FizzBuzz(
            int1 = 5,
            int2 = 3,
            limit = 4,
            str1 = str1,
            str2 = str2
        )

        val expected = listOf("1", "2", str2, "4")

        val result = underTest.execute(request)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return empty statistics when no requests have been made`() {
        val result = underTest.getMostFrequentRequest()

        assertThat(result).isNull()
    }

    @Test
    fun `should return most frequent request`() {
        val request1 = FizzBuzz(1, 2, 3, str1, str2)
        val request2 = FizzBuzz(4, 5, 6, str1, str2)
        val request3 = FizzBuzz(7, 8, 9, str1, str2)

        underTest.execute(request1)
        underTest.execute(request2)
        underTest.execute(request2)
        underTest.execute(request3)

        val result = underTest.getMostFrequentRequest()

        assertThat(result).isEqualTo(Pair(first = request2, second = 2))
    }

    @Test
    fun `should return any top frequent request if draw`() {
        val request1 = FizzBuzz(1, 1, 3, str1, str2)
        val request2 = FizzBuzz(2, 2, 6, str1, str2)
        val request3 = FizzBuzz(3, 3, 9, str1, str2)

        underTest.execute(request1)
        underTest.execute(request1)
        underTest.execute(request2)
        underTest.execute(request2)
        underTest.execute(request3)

        val result = underTest.getMostFrequentRequest()

        assertThat(result).isEqualTo(Pair(first = request1, second = 2))
    }
}
