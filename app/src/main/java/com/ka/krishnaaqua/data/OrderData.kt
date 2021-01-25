package com.ka.krishnaaqua.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderData(
        val StartDate: String,
        val EndDate: String,
        val quantity: Int,
        val price: Int,
        val id: String,
        val email: String,
        val mobile: String,
        val name: String,
) : Parcelable