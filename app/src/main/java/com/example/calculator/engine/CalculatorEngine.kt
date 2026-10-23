package com.example.calculator.calculator.engine

import com.example.calculator.calculator.model.AngleMode


class CalculatorEngine {


    fun calculate(
        expression: String,
        angleMode: AngleMode = AngleMode.DEG
    ): CalculationResult {

        return try {

            if (expression.isBlank()) {

                return CalculationResult.Error(
                    "Empty expression"
                )
            }


            val lexer = Lexer()

            val tokens =
                lexer.tokenize(expression)


            val parser =
                ExpressionParser(angleMode)


            val result =
                parser.parse(tokens)


            validateResult(result)


            val formatter =
                NumberFormatter()


            CalculationResult.Success(
                formatter
                    .format(result)
                    .toDouble()
            )


        } catch (exception: ArithmeticException) {


            CalculationResult.Error(
                exception.message
                    ?: "Arithmetic error"
            )


        } catch (exception: IllegalArgumentException) {


            CalculationResult.Error(
                exception.message
                    ?: "Invalid expression"
            )


        } catch (exception: Exception) {


            CalculationResult.Error(
                "Calculation failed"
            )
        }
    }


    private fun validateResult(
        value: Double
    ) {

        if (value.isNaN()) {

            throw ArithmeticException(
                "Invalid calculation"
            )
        }


        if (value.isInfinite()) {

            throw ArithmeticException(
                "Overflow"
            )
        }
    }
}