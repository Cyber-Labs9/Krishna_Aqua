package com.ka.krishnaaqua.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserData(
        val id: String,
        val name: String,
        val email: String,
        val password: String,
        val address: String,
        val mobile: String,
) : Parcelable