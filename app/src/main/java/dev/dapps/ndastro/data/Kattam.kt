package dev.dapps.ndastro.data

import java.util.Date

data class Kattam(
    val id: String,
    val title: String,
    val description: String,
    val datetime: Date,
    val user: String? = null
) {

}