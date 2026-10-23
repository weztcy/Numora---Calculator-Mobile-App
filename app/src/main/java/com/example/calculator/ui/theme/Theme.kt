package com.example.calculator.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable



private val DarkColorScheme = darkColorScheme(

    primary = PremiumBlue,

    secondary = PremiumGold,

    background = AMOLEDBlack,

    surface = GlassDark,

    onBackground = CalculatorTextPrimary,

    onSurface = CalculatorTextPrimary

)



@Composable
fun CalculatorTheme(

    content: @Composable () -> Unit

) {


    MaterialTheme(

        colorScheme = DarkColorScheme,

        typography = Typography,

        content = content

    )

}