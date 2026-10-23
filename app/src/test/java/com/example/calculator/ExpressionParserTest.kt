package com.example.calculator

import com.example.calculator.calculator.engine.ExpressionParser
import com.example.calculator.calculator.engine.Lexer
import com.example.calculator.calculator.model.AngleMode
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class ExpressionParserTest {


    private lateinit var lexer: Lexer

    private lateinit var parser: ExpressionParser


    @Before
    fun setup() {

        lexer = Lexer()

        parser = ExpressionParser(
            AngleMode.DEG
        )
    }



    private fun parse(
        expression: String
    ): Double {

        val tokens =
            lexer.tokenize(expression)

        return parser.parse(tokens)
    }



    @Test
    fun basicAddition_shouldParseCorrectly() {

        val result =
            parse("2+3")


        assertEquals(
            5.0,
            result,
            0.000001
        )
    }



    @Test
    fun multiplication_shouldHaveHigherPriority() {

        val result =
            parse("2+3*4")


        assertEquals(
            14.0,
            result,
            0.000001
        )
    }



    @Test
    fun parentheses_shouldChangePriority() {

        val result =
            parse("(2+3)*4")


        assertEquals(
            20.0,
            result,
            0.000001
        )
    }



    @Test
    fun unaryMinus_shouldWork() {

        val result =
            parse("-5+3")


        assertEquals(
            -2.0,
            result,
            0.000001
        )
    }



    @Test
    fun power_shouldBeRightAssociative() {

        val result =
            parse("2^3^2")


        assertEquals(
            512.0,
            result,
            0.000001
        )
    }



    @Test
    fun factorial_shouldParseCorrectly() {

        val result =
            parse("5!")


        assertEquals(
            120.0,
            result,
            0.000001
        )
    }



    @Test
    fun percentage_shouldParseCorrectly() {

        val result =
            parse("50%")


        assertEquals(
            0.5,
            result,
            0.000001
        )
    }



    @Test
    fun sqrtFunction_shouldParseCorrectly() {

        val result =
            parse("sqrt(16)")


        assertEquals(
            4.0,
            result,
            0.000001
        )
    }



    @Test
    fun trigonometry_shouldUseDegreeMode() {

        val result =
            parse("sin(30)")


        assertEquals(
            0.5,
            result,
            0.000001
        )
    }



    @Test
    fun constantPi_shouldParseCorrectly() {

        val result =
            parse("π")


        assertEquals(
            Math.PI,
            result,
            0.000001
        )
    }



    @Test
    fun implicitMultiplication_shouldWork() {

        val result =
            parse("2π")


        assertEquals(
            2 * Math.PI,
            result,
            0.000001
        )
    }

}