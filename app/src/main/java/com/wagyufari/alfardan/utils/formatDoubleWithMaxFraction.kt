package com.wagyufari.alfardan.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun formatDoubleWithMaxFraction(doubleValue: Double, maxFractionDigits: Int): String {
    val df = DecimalFormat()
    val dfs = DecimalFormatSymbols()
    df.decimalFormatSymbols = dfs
    df.maximumFractionDigits = maxFractionDigits
    return df.format(doubleValue).replace(",","")
}