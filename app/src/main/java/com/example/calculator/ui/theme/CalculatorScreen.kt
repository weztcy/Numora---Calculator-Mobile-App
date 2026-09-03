package com.example.calculator.ui.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.CalculatorViewModel

@Composable
fun CalculatorScreen(

    viewModel: CalculatorViewModel = viewModel(),

    modifier: Modifier = Modifier

) {


    val state by viewModel.uiState.collectAsState()



    Column(

        modifier = modifier

            .fillMaxSize()

            // Aman dari notification bar
            .statusBarsPadding()

            // Aman dari navbar bawah
            .navigationBarsPadding()

            // Aman ketika keyboard muncul
            .imePadding()

            .background(

                AMOLEDBlack

            )

            .padding(

                horizontal = 12.dp,

                vertical = 12.dp

            )

            .verticalScroll(

                rememberScrollState()

            ),



        verticalArrangement = Arrangement.SpaceBetween

    ) {



        /*
         *
         * DISPLAY AREA
         *
         */


        CalculatorDisplay(

            state = state,


            modifier = Modifier

                .weight(

                    if(state.scientificMode)

                        0.25f

                    else

                        0.35f

                )

        )





        /*
         *
         * KEYPAD AREA
         *
         */


        CalculatorKeypad(

            state = state,


            onAction = viewModel::onAction,


            modifier = Modifier

                .weight(

                    if(state.scientificMode)

                        0.75f

                    else

                        0.65f

                )

        )


    }

}