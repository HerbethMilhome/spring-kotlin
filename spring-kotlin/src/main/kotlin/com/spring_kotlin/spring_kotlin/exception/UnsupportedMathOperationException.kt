package com.spring_kotlin.spring_kotlin.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UnsupportedMathOperationException(exception: String?): RuntimeException(exception) {
}