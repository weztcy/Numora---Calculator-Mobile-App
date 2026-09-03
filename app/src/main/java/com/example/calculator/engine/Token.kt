package com.example.calculator.calculator.engine

import com.example.calculator.calculator.model.CalculatorOperator
import com.example.calculator.calculator.model.ScientificFunction

sealed class Token {

    data class Number(
        val value: Double
    ) : Token()


    data class Operator(
        val operator: CalculatorOperator
    ) : Token()


    data class Function(
        val function: ScientificFunction
    ) : Token()


    data class Constant(
        val name: String,
        val value: Double
    ) : Token()


    data object LeftParenthesis : Token()


    data object RightParenthesis : Token()


    data object Factorial : Token()


    data object Percentage : Token()
}