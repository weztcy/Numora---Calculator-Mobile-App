package com.example.calculator.calculator.model


data class CalculatorUiState(

    // Input expression
    val expression: String = "",


    // Calculation result
    val result: String = "",


    // Error message
    val errorMessage: String? = null,


    // Menampilkan apakah hasil sudah dihitung
    val isResultDisplayed: Boolean = false,


    // Scientific keypad mode
    val scientificMode: Boolean = false,


    // Degree / Radian mode
    val angleMode: AngleMode = AngleMode.DEG

)