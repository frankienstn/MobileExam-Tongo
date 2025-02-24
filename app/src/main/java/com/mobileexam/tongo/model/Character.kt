package com.mobileexam.tongo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val location: String,
    val origin: String
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String?,
    val gender: String,
    val image: String,
    val location: Location,
    val origin: Origin
)


data class Location(
    val name: String
)

data class Origin(
    val name: String,
    val type: String = ""
)

data class CharacterResponse(
    val results: List<Character>
)
