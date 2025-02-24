package com.mobileexam.tongo.data.repository

import com.mobileexam.tongo.data.remote.RickAndMortyApiService
import com.mobileexam.tongo.model.CharacterDao
import com.mobileexam.tongo.model.CharacterEntity
import kotlinx.coroutines.flow.Flow


class CharacterRepository(
    private val apiService: RickAndMortyApiService,
    private val characterDao: CharacterDao
) {
    fun getCharacters(): Flow<List<CharacterEntity>> = characterDao.getCharacters()


    suspend fun fetchCharactersFromApi() {
        try {
            val response = apiService.getCharacters()
            val entities = response.results.map { character ->
                CharacterEntity(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    type = character.type ?: "",
                    gender = character.gender,
                    image = character.image,
                    location = character.location.name,
                    origin = character.origin.name
                )
            }
            characterDao.insertAll(entities)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
