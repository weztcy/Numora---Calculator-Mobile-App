package com.example.calculator.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val Typography = Typography(

    // Text normal aplikasi
    bodyLarge = TextStyle(

        fontSize = 18.sp,

        fontWeight = FontWeight.Normal

    ),


    // Tombol calculator
    labelLarge = TextStyle(

        fontSize = 22.sp,

        fontWeight = FontWeight.Medium

    ),


    // Display hasil kalkulasi
    displayLarge = TextStyle(

        fontSize = 56.sp,

        fontWeight = FontWeight.Light

    ),


    // Expression kecil di atas hasil
    displayMedium = TextStyle(

        fontSize = 28.sp,

        fontWeight = FontWeight.Normal

    )

)