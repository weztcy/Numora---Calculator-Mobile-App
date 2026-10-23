package com.example.calculator

import com.example.calculator.calculator.CalculatorViewModel
import com.example.calculator.calculator.model.AngleMode
import com.example.calculator.calculator.model.CalculatorAction
import com.example.calculator.calculator.model.CalculatorOperator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CalculatorViewModelTest {


    private lateinit var viewModel: CalculatorViewModel


    @Before
    fun setup() {

        viewModel =
            CalculatorViewModel()

    }



    @Test
    fun numberInput_shouldUpdateExpression() {

        viewModel.onAction(
            CalculatorAction.NumberInput("5")
        )


        assertEquals(
            "5",
            viewModel.uiState.value.expression
        )
    }



    @Test
    fun operatorInput_shouldUpdateExpression() {

        viewModel.onAction(
            CalculatorAction.NumberInput("5")
        )

        viewModel.onAction(
            CalculatorAction.OperatorInput(
                CalculatorOperator.ADD
            )
        )


        assertEquals(
            "5+",
            viewModel.uiState.value.expression
        )
    }



    @Test
    fun calculate_shouldUpdateResult() {

        viewModel.onAction(
            CalculatorAction.NumberInput("2")
        )

        viewModel.onAction(
            CalculatorAction.OperatorInput(
                CalculatorOperator.ADD
            )
        )

        viewModel.onAction(
            CalculatorAction.NumberInput("3")
        )

        viewModel.onAction(
            CalculatorAction.Calculate
        )


        assertEquals(
            "5.0",
            viewModel.uiState.value.result
        )

        assertTrue(
            viewModel.uiState.value.isResultDisplayed
        )
    }



    @Test
    fun clear_shouldResetState() {

        viewModel.onAction(
            CalculatorAction.NumberInput("123")
        )


        viewModel.onAction(
            CalculatorAction.Clear
        )


        assertEquals(
            "",
            viewModel.uiState.value.expression
        )

        assertEquals(
            "",
            viewModel.uiState.value.result
        )
    }



    @Test
    fun backspace_shouldRemoveLastCharacter() {

        viewModel.onAction(
            CalculatorAction.NumberInput("123")
        )


        viewModel.onAction(
            CalculatorAction.Backspace
        )


        assertEquals(
            "12",
            viewModel.uiState.value.expression
        )
    }



    @Test
    fun toggleAngleMode_shouldChangeMode() {

        val initial =
            viewModel.uiState.value.angleMode


        viewModel.onAction(
            CalculatorAction.ToggleAngleMode
        )


        val updated =
            viewModel.uiState.value.angleMode


        assertEquals(
            AngleMode.RAD,
            updated
        )

        assertTrue(
            initial != updated
        )
    }



    @Test
    fun divisionByZero_shouldReturnError() {

        viewModel.onAction(
            CalculatorAction.NumberInput("5")
        )

        viewModel.onAction(
            CalculatorAction.OperatorInput(
                CalculatorOperator.DIVIDE
            )
        )

        viewModel.onAction(
            CalculatorAction.NumberInput("0")
        )

        viewModel.onAction(
            CalculatorAction.Calculate
        )


        assertTrue(
            viewModel.uiState.value.errorMessage
                    != null
        )
    }

}