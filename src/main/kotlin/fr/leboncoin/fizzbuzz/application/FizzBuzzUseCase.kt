package fr.leboncoin.fizzbuzz.application

import fr.leboncoin.fizzbuzz.domain.FizzBuzz
import org.springframework.stereotype.Service

@Service
class FizzBuzzUseCase {
    private val requests = hashMapOf<FizzBuzz, Int>()

    fun execute(request: FizzBuzz): List<String> {
        val result = mutableListOf<String>()
        for (i in 1..request.limit) {
            if (i % request.int1 == 0 && i % request.int2 == 0) {
                result.add(request.str1 + request.str2)
            } else if (i % request.int1 == 0) {
                result.add(request.str1)
            } else if (i % request.int2 == 0) {
                result.add(request.str2)
            } else {
                result.add(i.toString())
            }
        }
        fillStatistics(request)
        return result
    }

    private fun fillStatistics(request: FizzBuzz) {
        requests[request] = 1 + requests.getOrDefault(request, 0)
    }

    fun getMostFrequentRequest(): Pair<FizzBuzz, Int>? {
        val mostFrequent = requests.entries.maxByOrNull { it.value }
        return mostFrequent?.let { Pair(first = mostFrequent.key, second = mostFrequent.value) }
    }
}
