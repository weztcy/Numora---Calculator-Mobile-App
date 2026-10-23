package com.example.calculator.calculator.engine

import com.example.calculator.calculator.model.CalculatorOperator
import com.example.calculator.calculator.model.ScientificFunction

class Lexer {

    fun tokenize(expression: String): List<Token> {

        val tokens = mutableListOf<Token>()

        var index = 0

        while (index < expression.length) {

            val current = expression[index]


            when {

                // Ignore whitespace
                current.isWhitespace() -> {
                    index++
                }


                // Number
                current.isDigit() || current == '.' -> {

                    val numberResult = readNumber(
                        expression,
                        index
                    )

                    tokens.add(
                        Token.Number(
                            numberResult.first
                        )
                    )

                    index = numberResult.second
                }


                // Operators
                current == '+' -> {

                    tokens.add(
                        Token.Operator(
                            CalculatorOperator.ADD
                        )
                    )

                    index++
                }


                current == '-' -> {

                    tokens.add(
                        Token.Operator(
                            CalculatorOperator.SUBTRACT
                        )
                    )

                    index++
                }


                current == '*' || current == '×' -> {

                    tokens.add(
                        Token.Operator(
                            CalculatorOperator.MULTIPLY
                        )
                    )

                    index++
                }


                current == '/' || current == '÷' -> {

                    tokens.add(
                        Token.Operator(
                            CalculatorOperator.DIVIDE
                        )
                    )

                    index++
                }


                current == '^' -> {

                    tokens.add(
                        Token.Operator(
                            CalculatorOperator.POWER
                        )
                    )

                    index++
                }


                current == '%' -> {

                    tokens.add(
                        Token.Percentage
                    )

                    index++
                }


                current == '!' -> {

                    tokens.add(
                        Token.Factorial
                    )

                    index++
                }


                current == '(' -> {

                    tokens.add(
                        Token.LeftParenthesis
                    )

                    index++
                }


                current == ')' -> {

                    tokens.add(
                        Token.RightParenthesis
                    )

                    index++
                }


                // Constants and functions
                current.isLetter() || current == 'π' -> {

                    val wordResult = readWord(
                        expression,
                        index
                    )

                    when (wordResult.first) {

                        "pi",
                        "π" -> {

                            tokens.add(
                                Token.Constant(
                                    name = "pi",
                                    value = Math.PI
                                )
                            )
                        }


                        "e" -> {

                            tokens.add(
                                Token.Constant(
                                    name = "e",
                                    value = Math.E
                                )
                            )
                        }


                        else -> {

                            val function =
                                ScientificFunction.entries
                                    .find {
                                        it.symbol ==
                                                wordResult.first
                                    }

                            if (function != null) {

                                tokens.add(
                                    Token.Function(function)
                                )

                            } else {

                                throw IllegalArgumentException(
                                    "Unknown token: ${wordResult.first}"
                                )
                            }
                        }
                    }

                    index = wordResult.second
                }


                else -> {

                    throw IllegalArgumentException(
                        "Invalid character: $current"
                    )
                }
            }
        }


        return tokens
    }


    private fun readNumber(
        expression: String,
        start: Int
    ): Pair<Double, Int> {

        var index = start

        var dotCount = 0

        val builder = StringBuilder()


        while (index < expression.length) {

            val char = expression[index]

            when {

                char.isDigit() -> {
                    builder.append(char)
                }


                char == '.' -> {

                    dotCount++

                    if (dotCount > 1) {
                        throw IllegalArgumentException(
                            "Invalid decimal number"
                        )
                    }

                    builder.append(char)
                }


                else -> break
            }

            index++
        }


        return Pair(
            builder.toString().toDouble(),
            index
        )
    }


    private fun readWord(
        expression: String,
        start: Int
    ): Pair<String, Int> {

        var index = start

        val builder = StringBuilder()


        while (index < expression.length) {

            val char = expression[index]

            if (char.isLetter() || char == 'π') {

                builder.append(char)

                index++

            } else {

                break
            }
        }


        return Pair(
            builder.toString(),
            index
        )
    }
}