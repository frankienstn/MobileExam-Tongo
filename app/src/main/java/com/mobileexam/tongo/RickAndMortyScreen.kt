package com.mobileexam.tongo

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class RickAndMortyScreen : ViewModel() {
    private val _characters = mutableStateListOf<Character>()
    val characters: SnapshotStateList<Character> = _characters

    init {
        _characters.addAll(listOf(
            Character(
                name = "Rick Sanchez",
                imageResId = R.drawable.rick,
                status = "Alive",
                species = "Human",
                type = "Scientist",
                gender = "Male",
                location = "Earth",
                origin = "Earth"
            ),
            Character(
                name = "Morty Smith",
                imageResId = R.drawable.morty,
                status = "Alive",
                species = "Human",
                gender = "Male",
                location = "Earth",
                origin = "Earth"
            ),
            Character(
                name = "Summer Smith",
                imageResId = R.drawable.summer,
                status = "Alive",
                species = "Human",
                gender = "Female",
                location = "Earth (Replacement Dimension)",
                origin = "Earth (Replacement Dimension)",
            ),

            Character(
                name = "Beth Smith",
                imageResId = R.drawable.beth,
                status = "Alive",
                species = "Human",
                gender = "Female",
                location = "Earth (Replacement Dimension)",
                origin = "Earth (Replacement Dimension)",
            ),

            Character(
                name = "Jerry Smith",
                imageResId = R.drawable.jerry,
                status = "Alive",
                species = "Human",
                gender = "Male",
                location = "Earth (Replacement Dimension)",
                origin = "Earth (Replacement Dimension)",
            ),

            Character(
                name = "Abadango Cluster Princess",
                imageResId = R.drawable.abadango,
                status = "Alive",
                species = "Alien",
                gender = "Female",
                location = "Abadango",
                origin = "Abadango",
            ),

            Character(
                name = "Abradolf Lincler",
                imageResId = R.drawable.abradolf,
                status = "unknown",
                species = "Human",
                type = "Genetic experiment",
                gender = "Male",
                location = "Testicle Monster Dimension",
                origin = "Earth (Replacement Dimension)",
            ),

            Character(
                name = "Adjudicator Rick",
                imageResId = R.drawable.adjudicator,
                status = "Dead",
                species = "Human",
                gender = "Male",
                location = "Citadel of Ricks",
                origin = "unknown",
            ),

            Character(
                name = "Agency Director",
                imageResId = R.drawable.agency,
                status = "Dead",
                species = "Human",
                gender = "Male",
                location = "Earth (Replacement Dimension)",
                origin = "Earth (Replacement Dimension)",
            ),

            Character(
                name = "Alan Rails",
                imageResId = R.drawable.alan,
                status = "Dead",
                species = "Human",
                type = "Superhuman (Ghost trains summoner)",
                gender = "Male",
                location = "Worldender's lair",
                origin = "unknown",
            ),

            Character(
                name = "Albert Einstein",
                imageResId = R.drawable.albert,
                status = "Dead",
                species = "Human",
                gender = "Male",
                location = "Earth (Replacement Dimension)",
                origin = "Earth (C-137)",
            ),

            Character(
                name = "Alexander",
                imageResId = R.drawable.alexander,
                status = "Dead",
                species = "Human",
                gender = "Male",
                location = "Anatomy Park",
                origin = "Earth (C-137)",
            ),

            Character(
                name = "Alien Googah",
                imageResId = R.drawable.alien,
                status = "unknown",
                species = "Alien",
                gender = "unknown",
                location = "Earth (Replacement Dimension)",
                origin = "unknown",
            ),

            Character(
                name = "Alien Morty",
                imageResId = R.drawable.alienmorty,
                status = "unknown",
                species = "Alien",
                gender = "Male",
                location = "Citadel of Ricks",
                origin = "unknown",
            ),

            Character(
                name = "Alien Rick",
                imageResId = R.drawable.alienrick,
                status = "unknown",
                species = "Alien",
                gender = "Male",
                location = "Citadel of Ricks",
                origin = "unknown",
            ),

            Character(
                name = "Amish Cyborg",
                imageResId = R.drawable.amish,
                status = "Dead",
                species = "Alien",
                type = "Parasite",
                gender = "Male",
                location = "Earth (Replacement Dimension)",
                origin = "unknown",
            ),

            Character(
                name = "Annie",
                imageResId = R.drawable.annie,
                status = "Alive",
                species = "Human",
                gender = "Female",
                location = "Anatomy Park",
                origin = "Earth (C-137)",
            ),

            Character(
                name = "Antenna Morty",
                imageResId = R.drawable.antenna,
                status = "Alive",
                species = "Human",
                type = "Human with antennae",
                gender = "Male",
                location = "Citadel of Ricks",
                origin = "unknown",
            ),

            Character(
                name = "Antenna Rick",
                imageResId = R.drawable.antennarick,
                status = "unknown",
                species = "Human",
                type = "Human with antennae",
                gender = "Male",
                location = "unknown",
                origin = "unknown",
            ),

            Character(
                name = "Ants in my Eyes Johnson",
                imageResId = R.drawable.ants,
                status = "unknown",
                species = "Human",
                type = "Human with ants in his eyes",
                gender = "Male",
                location = "Interdimensional Cable",
                origin = "unknown",
            ),
            )
        )
    }
}
