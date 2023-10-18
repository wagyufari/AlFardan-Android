package com.wagyufari.alfardan.ui.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val code: String?,
    val flag: String?,
    val exchangeRateToAed: Double?
): Parcelable
