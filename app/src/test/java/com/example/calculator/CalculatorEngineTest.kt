package com.example.calculator

import com.example.calculator.calculator.engine.CalculationResult
import com.example.calculator.calculator.engine.CalculatorEngine
import com.example.calculator.calculator.model.AngleMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class CalculatorEngineTest {


    private lateinit var engine: CalculatorEngine


    @Before
    fun setup() {

        engine = CalculatorEngine()

    }


    @Test
    fun addition_shouldReturnCorrectResult() {

        val result =
            engine.calculate("2+3")


        assertEquals(
            5.0,
            getValue(result),
            0.000001
        )
    }



    @Test
    fun operatorPrecedence_shouldMultiplyFirst() {

        val result =
            engine.calculate("2+3*4")


        assertEquals(
            14.0,
            getValue(result),
            0.000001
        )
    }



    @Test
    fun parentheses_shouldOverridePrecedence() {

        val result =
            engine.calculate("(2+3)*4")


        assertEquals(
            20.0,
            getValue(result),
            0.000001
        )
    }



    @Test
    fun power_shouldWorkCorrectly() {

        val result =
            engine.calculate("2^3")


        assertEquals(
            8.0,
            getValue(result),
            0.000001
        )
    }



    @Test
    fun power_shouldBeRightAssociative() {

        val result =
            engine.calculate("2^3^2")


        assertEquals(
            512.0,
            getValue(result),
            0.000001
        )
    }



    @Test
    fun factorial_shouldReturnCorrectResult() {

        val result =
            engine.calculate("5!")


        assertEquals(
            120.0,
            getValue(result),
            0.000001
        )
    }



    @Test
    fun squareRoot_shouldReturnCorrectResult() {

        val result =
            engine.calculate("sqrt(25)")


        assertEquals(
            5.0,
            getValue(result),
            0.000001
        )
    }



    @Test
    fun sine_degreeMode_shouldReturnCorrectResult() {

        val result =
            engine.calculate(
                expression = "sin(30)",
                angleMode = AngleMode.DEG
            )


        assertEquals(
            0.5,
            getValue(result),
            0.000001
        )
    }



    @Test
    fun divisionByZero_shouldReturnError() {

        val result =
            engine.calculate("5/0")


        assertTrue(
            result is CalculationResult.Error
        )
    }



    @Test
    fun invalidSquareRoot_shouldReturnError() {

        val result =
            engine.calculate("sqrt(-1)")


        assertTrue(
            result is CalculationResult.Error
        )
    }



    private fun getValue(
        result: CalculationResult
    ): Double {


        return when (result) {

            is CalculationResult.Success ->
                result.value


            is CalculationResult.Error ->
                throw AssertionError(
                    result.message
                )
        }
    }
}