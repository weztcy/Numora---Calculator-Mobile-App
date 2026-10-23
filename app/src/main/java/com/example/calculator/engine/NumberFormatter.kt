package com.example.calculator.calculator.engine

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs


class NumberFormatter {


    fun format(value: Double): String {

        if (value.isNaN()) {
            return "NaN"
        }


        if (value.isInfinite()) {
            return "Infinity"
        }


        // Handle negative zero
        if (abs(value) < 1e-12) {
            return "0"
        }


        return when {

            // Very large / very small number
            abs(value) >= 1e12 ||
                    abs(value) < 1e-10 -> {

                formatScientific(value)
            }


            else -> {

                formatDecimal(value)
            }
        }
    }


    private fun formatDecimal(
        value: Double
    ): String {

        return BigDecimal
            .valueOf(value)
            .setScale(
                12,
                RoundingMode.HALF_UP
            )
            .stripTrailingZeros()
            .toPlainString()
    }


    private fun formatScientific(
        value: Double
    ): String {

        return String.format(
            "%.10E",
            value
        )
            .replace(
                "E+",
                "E"
            )
    }
}