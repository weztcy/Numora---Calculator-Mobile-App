package com.example.calculator.ui.theme


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calculator.calculator.model.AngleMode
import com.example.calculator.calculator.model.CalculatorAction
import com.example.calculator.calculator.model.CalculatorOperator
import com.example.calculator.calculator.model.CalculatorUiState
import com.example.calculator.calculator.model.ScientificFunction



@Composable
fun CalculatorKeypad(

    state: CalculatorUiState,

    onAction: (CalculatorAction) -> Unit,

    modifier: Modifier = Modifier

) {


    Column(

        modifier = modifier,

        verticalArrangement = Arrangement.spacedBy(6.dp)

    ) {



        // =========================
        // CONTROL BUTTON
        // =========================


        CalculatorRow {


            CalculatorButton(

                text = if (state.scientificMode)
                    "STD"
                else
                    "SCI",


                type = CalculatorButtonType.FUNCTION,


                scientificMode = state.scientificMode,


                onClick = {

                    onAction(
                        CalculatorAction.ToggleScientificMode
                    )

                }

            )



            CalculatorButton(

                text = if (state.angleMode == AngleMode.DEG)
                    "DEG"
                else
                    "RAD",


                type = CalculatorButtonType.FUNCTION,


                scientificMode = state.scientificMode,


                onClick = {

                    onAction(
                        CalculatorAction.ToggleAngleMode
                    )

                }

            )



            CalculatorButton(

                text = "AC",


                type = CalculatorButtonType.FUNCTION,


                scientificMode = state.scientificMode,


                onClick = {

                    onAction(
                        CalculatorAction.Clear
                    )

                }

            )



            CalculatorButton(

                text = "⌫",


                type = CalculatorButtonType.FUNCTION,


                scientificMode = state.scientificMode,


                onClick = {

                    onAction(
                        CalculatorAction.Backspace
                    )

                }

            )


        }




        // =========================
        // SCIENTIFIC PANEL
        // =========================


        AnimatedVisibility(

            visible = state.scientificMode,


            enter = expandVertically(),


            exit = shrinkVertically()

        ) {


            Column(

                verticalArrangement =
                    Arrangement.spacedBy(6.dp)

            ) {



                CalculatorRow {


                    ScientificButton(
                        "sin",
                        ScientificFunction.SIN,
                        onAction
                    )


                    ScientificButton(
                        "cos",
                        ScientificFunction.COS,
                        onAction
                    )


                    ScientificButton(
                        "tan",
                        ScientificFunction.TAN,
                        onAction
                    )



                    CalculatorButton(

                        text = "√",


                        type = CalculatorButtonType.FUNCTION,


                        scientificMode = true,


                        onClick = {

                            onAction(
                                CalculatorAction.SquareRoot
                            )

                        }

                    )

                }





                CalculatorRow {



                    CalculatorButton(

                        text = "^",


                        type = CalculatorButtonType.FUNCTION,


                        scientificMode = true,


                        onClick = {

                            onAction(
                                CalculatorAction.Power
                            )

                        }

                    )





                    CalculatorButton(

                        text = "!",


                        type = CalculatorButtonType.FUNCTION,


                        scientificMode = true,


                        onClick = {

                            onAction(
                                CalculatorAction.Factorial
                            )

                        }

                    )





                    CalculatorButton(

                        text = "π",


                        type = CalculatorButtonType.FUNCTION,


                        scientificMode = true,


                        onClick = {

                            onAction(
                                CalculatorAction.NumberInput("π")
                            )

                        }

                    )





                    CalculatorButton(

                        text = "e",


                        type = CalculatorButtonType.FUNCTION,


                        scientificMode = true,


                        onClick = {

                            onAction(
                                CalculatorAction.NumberInput("e")
                            )

                        }

                    )


                }


            }

        }





        // =========================
        // NUMBER PAD
        // =========================


        CalculatorRow {


            NumberButton("7", onAction)

            NumberButton("8", onAction)

            NumberButton("9", onAction)


            OperatorButton(

                "÷",

                CalculatorOperator.DIVIDE,

                onAction

            )


        }




        CalculatorRow {


            NumberButton("4", onAction)

            NumberButton("5", onAction)

            NumberButton("6", onAction)


            OperatorButton(

                "×",

                CalculatorOperator.MULTIPLY,

                onAction

            )


        }





        CalculatorRow {


            NumberButton("1", onAction)

            NumberButton("2", onAction)

            NumberButton("3", onAction)


            OperatorButton(

                "-",

                CalculatorOperator.SUBTRACT,

                onAction

            )


        }





        CalculatorRow {


            CalculatorButton(

                text = ".",


                scientificMode = false,


                onClick = {

                    onAction(
                        CalculatorAction.DecimalInput
                    )

                }

            )




            NumberButton(

                "0",

                onAction

            )





            CalculatorButton(

                text = "=",


                type = CalculatorButtonType.EQUAL,


                scientificMode = false,


                onClick = {

                    onAction(
                        CalculatorAction.Calculate
                    )

                }

            )





            OperatorButton(

                "+",

                CalculatorOperator.ADD,

                onAction

            )


        }


    }

}






// =================================================
// ROW WRAPPER
// =================================================


@Composable
private fun CalculatorRow(

    content: @Composable RowScope.() -> Unit

) {


    Row(

        modifier = Modifier.fillMaxWidth(),


        horizontalArrangement =
            Arrangement.SpaceEvenly

    ) {


        content()

    }

}






// =================================================
// NUMBER BUTTON
// =================================================


@Composable
private fun RowScope.NumberButton(

    value: String,

    onAction: (CalculatorAction) -> Unit

) {


    CalculatorButton(

        text = value,


        scientificMode = false,


        onClick = {


            onAction(
                CalculatorAction.NumberInput(value)
            )

        }

    )

}






// =================================================
// OPERATOR BUTTON
// =================================================


@Composable
private fun RowScope.OperatorButton(

    text: String,

    operator: CalculatorOperator,

    onAction: (CalculatorAction) -> Unit

) {


    CalculatorButton(

        text = text,


        type = CalculatorButtonType.OPERATOR,


        scientificMode = false,


        onClick = {


            onAction(
                CalculatorAction.OperatorInput(operator)
            )

        }

    )

}






// =================================================
// SCIENTIFIC BUTTON
// =================================================


@Composable
private fun RowScope.ScientificButton(

    text: String,

    function: ScientificFunction,

    onAction: (CalculatorAction) -> Unit

) {


    CalculatorButton(

        text = text,


        type = CalculatorButtonType.FUNCTION,


        scientificMode = true,


        onClick = {


            onAction(
                CalculatorAction.FunctionInput(function)
            )

        }

    )

}