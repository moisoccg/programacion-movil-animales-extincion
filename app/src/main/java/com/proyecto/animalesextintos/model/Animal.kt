package com.proyecto.animalesextintos.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal (
    var id: String,
    val nombre: String
): Parcelable {
    constructor(): this("","")
}