package com.ka.krishnaaqua.data

import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
        var id: String,
        var name: String,
        var email: String,
        var password: String,
        var address: String,
        var mobile: String,
)