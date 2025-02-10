package com.mobileexam.tongo

data class Character(
    val name: String,
    val imageResId: Int? = null,
    val status: String,
    val species: String,
    val type: String? = null,
    val gender: String? = null,
    val location: String? = null,
    val origin: String? = null
)

