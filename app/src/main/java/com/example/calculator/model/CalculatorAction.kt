package com.example.calculator.calculator.model


sealed class CalculatorAction {


    // =========================
    // Input
    // =========================

    data class NumberInput(
        val number: String
    ) : CalculatorAction()



    data object DecimalInput : CalculatorAction()



    data class OperatorInput(
        val operator: CalculatorOperator
    ) : CalculatorAction()



    data class FunctionInput(
        val function: ScientificFunction
    ) : CalculatorAction()



    // =========================
    // Calculator Commands
    // =========================


    data object Calculate : CalculatorAction()



    data object Clear : CalculatorAction()



    data object Backspace : CalculatorAction()



    data object SquareRoot : CalculatorAction()



    data object Power : CalculatorAction()



    data object Factorial : CalculatorAction()



    // =========================
    // Advanced Mode
    // =========================


    /**
     * Toggle Basic ↔ Scientific keypad
     */
    data object ToggleScientificMode : CalculatorAction()



    /**
     * Toggle DEG ↔ RAD
     */
    data object ToggleAngleMode : CalculatorAction()

}