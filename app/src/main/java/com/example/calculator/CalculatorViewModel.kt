package com.example.calculator

import androidx.lifecycle.ViewModel
import com.example.calculator.calculator.engine.CalculationResult
import com.example.calculator.calculator.engine.CalculatorEngine
import com.example.calculator.calculator.model.AngleMode
import com.example.calculator.calculator.model.CalculatorAction
import com.example.calculator.calculator.model.CalculatorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CalculatorViewModel : ViewModel() {


    private val calculatorEngine =
        CalculatorEngine()



    private val _uiState =
        MutableStateFlow(
            CalculatorUiState()
        )



    val uiState: StateFlow<CalculatorUiState> =
        _uiState.asStateFlow()





    fun onAction(
        action: CalculatorAction
    ) {


        when(action) {


            is CalculatorAction.NumberInput -> {

                appendExpression(
                    action.number
                )

            }



            CalculatorAction.DecimalInput -> {

                appendExpression(
                    "."
                )

            }



            is CalculatorAction.OperatorInput -> {

                appendExpression(
                    action.operator.symbol
                )

            }



            is CalculatorAction.FunctionInput -> {

                appendExpression(
                    action.function.symbol + "("
                )

            }



            CalculatorAction.Calculate -> {

                calculate()

            }



            CalculatorAction.Clear -> {

                clear()

            }



            CalculatorAction.Backspace -> {

                backspace()

            }



            CalculatorAction.SquareRoot -> {

                appendExpression(
                    "sqrt("
                )

            }



            CalculatorAction.Power -> {

                appendExpression(
                    "^"
                )

            }



            CalculatorAction.Factorial -> {

                appendExpression(
                    "!"
                )

            }



            CalculatorAction.ToggleScientificMode -> {

                toggleScientificMode()

            }



            CalculatorAction.ToggleAngleMode -> {

                toggleAngleMode()

            }

        }

    }






    private fun appendExpression(
        value: String
    ) {


        _uiState.value =
            _uiState.value.copy(

                expression =
                    _uiState.value.expression + value,


                result = "",


                errorMessage = null,


                isResultDisplayed = false

            )

    }







    private fun calculate() {


        val expression =
            _uiState.value.expression



        if(expression.isBlank()) {

            return

        }




        when(

            val calculation =
                calculatorEngine.calculate(

                    expression,

                    _uiState.value.angleMode

                )

        ) {



            is CalculationResult.Success -> {


                _uiState.value =
                    _uiState.value.copy(

                        result =
                            calculation.value.toString(),


                        errorMessage = null,


                        isResultDisplayed = true

                    )

            }




            is CalculationResult.Error -> {


                _uiState.value =
                    _uiState.value.copy(

                        result = "",


                        errorMessage =
                            calculation.message,


                        isResultDisplayed = false

                    )

            }

        }

    }








    private fun clear() {


        _uiState.value =
            _uiState.value.copy(

                expression = "",

                result = "",

                errorMessage = null,

                isResultDisplayed = false

            )

    }








    private fun backspace() {


        val current =
            _uiState.value.expression



        if(current.isNotEmpty()) {


            _uiState.value =
                _uiState.value.copy(

                    expression =
                        current.dropLast(1),

                    result = "",

                    errorMessage = null

                )

        }

    }








    private fun toggleScientificMode() {


        _uiState.value =
            _uiState.value.copy(

                scientificMode =
                    !_uiState.value.scientificMode

            )

    }








    private fun toggleAngleMode() {


        val current =
            _uiState.value.angleMode



        val newMode =

            if(current == AngleMode.DEG)

                AngleMode.RAD

            else

                AngleMode.DEG




        _uiState.value =
            _uiState.value.copy(

                angleMode = newMode

            )

    }


}