package com.example.calculator.ui.theme


import android.view.HapticFeedbackConstants
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun RowScope.CalculatorButton(

    text: String,

    onClick: () -> Unit,

    type: CalculatorButtonType = CalculatorButtonType.NUMBER,

    scientificMode: Boolean = false

) {


    val view = LocalView.current



    var pressed by remember {

        mutableStateOf(false)

    }



    val scale by animateFloatAsState(

        targetValue = if (pressed)

            0.92f

        else

            1f,


        animationSpec = spring(),


        label = "button_scale"

    )





    // =============================
    // BUTTON COLOR
    // =============================

    // =============================
// BUTTON COLOR
// =============================

    val buttonColor = when {


        // DELETE BUTTON
        text == "⌫" || text == "AC" ->
            DeleteButtonColor



        // SCI / STD MODE BUTTON ONLY
        text == "SCI" ||
                text == "STD" ->

            ModeButtonColor



        // NORMAL TYPE COLOR
        type == CalculatorButtonType.NUMBER ->

            NumberButtonColor



        type == CalculatorButtonType.OPERATOR ->

            OperatorButtonColor



        type == CalculatorButtonType.FUNCTION ->

            FunctionButtonColor



        type == CalculatorButtonType.EQUAL ->

            EqualButtonColor



        else ->

            NumberButtonColor

    }






    // =============================
    // BUTTON SHAPE
    // =============================

    val buttonShape = when {


        // scientific function menjadi kotak rounded

        scientificMode &&

                type == CalculatorButtonType.FUNCTION ->

            RoundedCornerShape(18.dp)



        // mode button tetap rounded

        type == CalculatorButtonType.MODE ->

            RoundedCornerShape(18.dp)



        // delete rounded ketika scientific

        type == CalculatorButtonType.DELETE && scientificMode ->

            RoundedCornerShape(18.dp)



        else ->

            CircleShape

    }






    Button(

        onClick = {


            view.performHapticFeedback(

                HapticFeedbackConstants.KEYBOARD_TAP

            )


            pressed = true


            onClick()


            pressed = false

        },



        modifier = Modifier

            .weight(1f)

            .padding(5.dp)

            .scale(scale)

            .aspectRatio(

                when {


                    // tombol scientific melebar

                    scientificMode &&

                            type == CalculatorButtonType.FUNCTION ->

                        1.8f



                    type == CalculatorButtonType.MODE ->

                        1.5f



                    else ->

                        1f

                }

            ),



        shape = buttonShape,



        colors = ButtonDefaults.buttonColors(

            containerColor = buttonColor,

            contentColor = Color.White

        ),



        elevation = ButtonDefaults.buttonElevation(

            defaultElevation = 8.dp,

            pressedElevation = 2.dp

        )

    ) {



        Text(

            text = text,


            maxLines = 1,


            softWrap = false,


            textAlign = TextAlign.Center,



            fontSize = when {


                text.length >= 5 ->

                    11.sp



                text.length == 4 ->

                    13.sp



                type == CalculatorButtonType.FUNCTION ->

                    25.sp



                else ->

                    40.sp

            }

        )

    }

}