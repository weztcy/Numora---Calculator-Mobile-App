package com.example.calculator.calculator.engine

sealed class CalculationResult {

    data class Success(
        val value: Double
    ) : CalculationResult()


    data class Error(
        val message: String
    ) : CalculationResult()
}