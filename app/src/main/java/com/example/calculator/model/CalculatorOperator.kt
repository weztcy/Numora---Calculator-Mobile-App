package com.example.calculator.calculator.model

enum class CalculatorOperator(
    val symbol: String
) {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    POWER("^"),
    PERCENT("%")
}