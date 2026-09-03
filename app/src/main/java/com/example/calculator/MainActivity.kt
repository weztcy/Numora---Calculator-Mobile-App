package com.example.calculator

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.calculator.ui.theme.CalculatorScreen
import com.example.calculator.ui.theme.CalculatorTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)


        // Jangan gunakan enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(
            window,
            true
        )


        // Status bar atas hitam
        window.statusBarColor = Color.BLACK


        // Navigation bar bawah hitam
        window.navigationBarColor = Color.BLACK


        // Hilangkan warna abu-abu transparan Android
        window.isNavigationBarContrastEnforced = false


        // Icon menjadi putih
        WindowCompat.getInsetsController(
            window,
            window.decorView
        ).apply {

            isAppearanceLightStatusBars = false

            isAppearanceLightNavigationBars = false

        }


        setContent {

            CalculatorTheme {

                CalculatorScreen(

                    modifier = Modifier
                        .fillMaxSize()

                )

            }

        }

    }
}