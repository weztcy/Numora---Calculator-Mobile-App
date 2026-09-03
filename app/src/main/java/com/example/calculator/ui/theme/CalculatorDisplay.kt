package com.example.calculator.ui.theme


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.calculator.model.CalculatorUiState



@Composable
fun CalculatorDisplay(

    state: CalculatorUiState,

    modifier: Modifier = Modifier

) {


    Surface(

        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),


        shape = RoundedCornerShape(32.dp),


        color = DisplayBackground,


        shadowElevation = 12.dp

    ) {


        Column(

            modifier = Modifier

                .fillMaxWidth()

                .padding(

                    horizontal = 24.dp,

                    vertical = 28.dp

                ),


            horizontalAlignment = Alignment.End,


            verticalArrangement = Arrangement.Center

        ) {



            // =========================
            // EXPRESSION
            // =========================


            Text(

                text = state.expression.ifEmpty {

                    "0"

                },


                modifier = Modifier

                    .fillMaxWidth(),


                color = CalculatorTextSecondary,


                fontSize = displayTextSize(

                    state.expression

                ),


                textAlign = TextAlign.End,


                maxLines = 1

            )





            // =========================
            // RESULT
            // =========================


            AnimatedContent(

                targetState =

                    if (state.errorMessage != null)

                        state.errorMessage!!

                    else

                        state.result.ifEmpty {

                            "0"

                        },


                transitionSpec = {

                    fadeIn() togetherWith fadeOut()

                },


                label = "calculator_result"

            ) { result ->



                Text(

                    text = result,


                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(top = 12.dp),


                    color =

                        if (state.errorMessage != null)

                            MaterialTheme.colorScheme.error

                        else

                            CalculatorTextPrimary,


                    fontSize = resultTextSize(result),


                    textAlign = TextAlign.End,


                    maxLines = 1

                )


            }


        }

    }

}





private fun displayTextSize(

    text: String

) = when {


    text.length > 25 ->

        14.sp


    text.length > 15 ->

        18.sp


    else ->

        22.sp

}






private fun resultTextSize(

    text: String

) = when {


    text.length > 18 ->

        30.sp


    text.length > 12 ->

        40.sp


    else ->

        52.sp

}