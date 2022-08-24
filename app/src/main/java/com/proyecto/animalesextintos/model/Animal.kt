package com.proyecto.animalesextintos.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal (
    var id: String,
    val nombre: String,
    val raza: String,
    val tipo: String,
    val lugar: String
): Parcelable {
    constructor(): this("","","","","")
}