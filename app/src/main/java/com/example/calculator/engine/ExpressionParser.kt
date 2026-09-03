package com.example.calculator.calculator.engine

import com.example.calculator.calculator.model.AngleMode
import com.example.calculator.calculator.model.CalculatorOperator
import com.example.calculator.calculator.model.ScientificFunction
import kotlin.math.*

class ExpressionParser(
    private val angleMode: AngleMode = AngleMode.DEG
) {

    private lateinit var tokens: List<Token>

    private var position = 0


    fun parse(
        tokens: List<Token>
    ): Double {

        this.tokens = tokens
        position = 0

        val result = parseExpression()

        if (position < tokens.size) {
            throw IllegalArgumentException(
                "Unexpected token"
            )
        }

        return result
    }


    /*
        expression:
        addition / subtraction
     */
    private fun parseExpression(): Double {

        var value = parseTerm()


        while (position < tokens.size) {

            when (val token = tokens[position]) {

                is Token.Operator -> {

                    when (token.operator) {

                        CalculatorOperator.ADD -> {

                            position++

                            value += parseTerm()
                        }


                        CalculatorOperator.SUBTRACT -> {

                            position++

                            value -= parseTerm()
                        }


                        else -> return value
                    }
                }


                else -> return value
            }
        }


        return value
    }


    /*
        multiplication / division
     */
    private fun parseTerm(): Double {

        var value = parseFactor()


        while (position < tokens.size) {

            when (val token = tokens[position]) {

                is Token.Operator -> {

                    when (token.operator) {

                        CalculatorOperator.MULTIPLY -> {

                            position++

                            value *= parseFactor()
                        }


                        CalculatorOperator.DIVIDE -> {

                            position++

                            val divisor =
                                parseFactor()

                            if (divisor == 0.0) {
                                throw ArithmeticException(
                                    "Division by zero"
                                )
                            }

                            value /= divisor
                        }


                        else -> return value
                    }
                }


                /*
                    implicit multiplication

                    Example:
                    2π
                    2(5)
                */
                Token.LeftParenthesis,
                is Token.Constant,
                is Token.Function -> {

                    value *= parseFactor()
                }


                else -> return value
            }
        }


        return value
    }


    /*
        power
        unary
        postfix
     */
    private fun parseFactor(): Double {

        var value = parseUnary()


        while (position < tokens.size) {

            when (tokens[position]) {

                Token.Factorial -> {

                    position++

                    value = factorial(value)
                }


                Token.Percentage -> {

                    position++

                    value /= 100
                }


                else -> break
            }
        }


        return value
    }


    /*
        unary minus
     */
    private fun parseUnary(): Double {

        if (
            position < tokens.size &&
            tokens[position] is Token.Operator
        ) {

            val token =
                tokens[position] as Token.Operator


            if (
                token.operator ==
                CalculatorOperator.SUBTRACT
            ) {

                position++

                return -parseUnary()
            }
        }


        return parsePower()
    }


    /*
        right associative power

        2^3^2

        =

        2^(3^2)
     */
    private fun parsePower(): Double {

        var value = parsePrimary()


        if (
            position < tokens.size &&
            tokens[position] is Token.Operator
        ) {

            val token =
                tokens[position]
                        as Token.Operator


            if (
                token.operator ==
                CalculatorOperator.POWER
            ) {

                position++

                value = value.pow(
                    parsePower()
                )
            }
        }


        return value
    }


    private fun parsePrimary(): Double {


        if (position >= tokens.size) {

            throw IllegalArgumentException(
                "Unexpected end of expression"
            )
        }


        return when (
            val token = tokens[position]
        ) {


            is Token.Number -> {

                position++

                token.value
            }


            is Token.Constant -> {

                position++

                token.value
            }


            is Token.Function -> {

                position++

                parseFunction(
                    token.function
                )
            }


            Token.LeftParenthesis -> {

                position++

                val result =
                    parseExpression()


                if (
                    position >= tokens.size ||
                    tokens[position] !=
                    Token.RightParenthesis
                ) {

                    throw IllegalArgumentException(
                        "Missing closing parenthesis"
                    )
                }


                position++

                result
            }


            else -> {

                throw IllegalArgumentException(
                    "Invalid expression"
                )
            }
        }
    }


    private fun parseFunction(
        function: ScientificFunction
    ): Double {


        if (
            position >= tokens.size ||
            tokens[position] !=
            Token.LeftParenthesis
        ) {

            throw IllegalArgumentException(
                "Function requires parentheses"
            )
        }


        position++


        val argument =
            parseExpression()


        if (
            position >= tokens.size ||
            tokens[position] !=
            Token.RightParenthesis
        ) {

            throw IllegalArgumentException(
                "Missing function parenthesis"
            )
        }


        position++


        return calculateFunction(
            function,
            argument
        )
    }


    private fun calculateFunction(
        function: ScientificFunction,
        value: Double
    ): Double {


        val radians =
            if (angleMode == AngleMode.DEG)
                Math.toRadians(value)
            else
                value


        return when (function) {

            ScientificFunction.SIN ->
                sin(radians)


            ScientificFunction.COS ->
                cos(radians)


            ScientificFunction.TAN -> {

                val cosine =
                    cos(radians)

                if (abs(cosine) < 1e-10) {

                    throw ArithmeticException(
                        "Undefined tangent"
                    )
                }

                tan(radians)
            }


            ScientificFunction.ASIN -> {

                val result =
                    asin(value)

                convertAngle(result)
            }


            ScientificFunction.ACOS -> {

                val result =
                    acos(value)

                convertAngle(result)
            }


            ScientificFunction.ATAN -> {

                val result =
                    atan(value)

                convertAngle(result)
            }


            ScientificFunction.LOG ->
                log10(value)


            ScientificFunction.LN ->
                ln(value)


            ScientificFunction.SQRT -> {

                if (value < 0) {

                    throw ArithmeticException(
                        "Invalid square root"
                    )
                }

                sqrt(value)
            }
        }
    }


    private fun convertAngle(
        radians: Double
    ): Double {

        return if (
            angleMode == AngleMode.DEG
        ) {

            Math.toDegrees(radians)

        } else {

            radians
        }
    }


    private fun factorial(
        value: Double
    ): Double {


        if (
            value < 0 ||
            value % 1 != 0.0
        ) {

            throw ArithmeticException(
                "Invalid factorial"
            )
        }


        if (value > 170) {

            throw ArithmeticException(
                "Factorial overflow"
            )
        }


        var result = 1.0

        for (
        i in 1..value.toInt()
        ) {

            result *= i
        }


        return result
    }
}