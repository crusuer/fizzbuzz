package fr.leboncoin.fizzbuzz

import fr.leboncoin.fizzbuzz.Application
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
abstract class AbstractIntegrationTest {
    @LocalServerPort
    private val serverPort: Int = 0

    @BeforeEach
    fun startUp() {
        RestAssured.port = serverPort
    }
}
