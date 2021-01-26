package com.ka.krishnaaqua.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderCompleted(
        var startDate: String,
        var endDate: String,
        var id: Int,
        var total: Int,
) : Parcelable