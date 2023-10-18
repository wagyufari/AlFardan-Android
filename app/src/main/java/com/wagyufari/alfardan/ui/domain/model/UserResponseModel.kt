package com.wagyufari.alfardan.ui.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class UserResponseModel(
    val token: String?,
    val user: User?
)

@Parcelize
data class User(
    val id: String?,
    val email: String?,
    val name: String?,
    val balance: String?,
    val profilePicture: String?
): Parcelable
