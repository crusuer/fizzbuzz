package fr.leboncoin.fizzbuzz.adapter.input.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleRuntimeException(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(
            ex.message ?: "illegal arguments provided"
        )
    }

    @ExceptionHandler(value = [RuntimeException::class])
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<String> {
        return ResponseEntity.internalServerError().body(
            ex.message ?: "something went wrong"
        )
    }
}
