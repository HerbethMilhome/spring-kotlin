package com.spring_kotlin.spring_kotlin.controller

import com.spring_kotlin.spring_kotlin.exception.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController {

    val counter: AtomicLong = AtomicLong()

    @RequestMapping(value = ["/sum/{numUm}/{numDois}"])
    fun sum(@PathVariable(value = "numUm") numUm: String?,
                 @PathVariable(value = "numDois") numDois: String?): Double {

        return operate(numUm, numDois) { a, b -> a + b }
    }

    @RequestMapping(value = ["/sub/{numUm}/{numDois}"])
    fun sub(@PathVariable(value = "numUm") numUm: String?,
            @PathVariable(value = "numDois") numDois: String?): Double {

        return operate(numUm, numDois) { a, b -> a - b }
    }

    @RequestMapping(value = ["/mult/{numUm}/{numDois}"])
    fun mult(@PathVariable(value = "numUm") numUm: String?,
            @PathVariable(value = "numDois") numDois: String?): Double {

        return operate(numUm, numDois) { a, b -> a * b }
    }

    @RequestMapping(value = ["/div/{numUm}/{numDois}"])
    fun div(@PathVariable(value = "numUm") numUm: String?,
            @PathVariable(value = "numDois") numDois: String?): Double {

        return operate(numUm, numDois) { a, b ->
            if (b == 0.0) throw ArithmeticException("Divisão por zero")
            a / b
        }
    }

    private fun validateAndParse(number: String?): Double {
        return number
            ?.replace(",", ".")
            ?.toDoubleOrNull()
            ?: throw UnsupportedMathOperationException("Número inválido: $number")
    }

    private fun operate(
        numUm: String?,
        numDois: String?,
        operation: (Double, Double) -> Double
    ): Double {
        val number1 = validateAndParse(numUm)
        val number2 = validateAndParse(numDois)
        return operation(number1, number2)
    }

}