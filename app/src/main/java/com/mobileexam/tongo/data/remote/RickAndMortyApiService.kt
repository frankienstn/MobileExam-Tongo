package com.mobileexam.tongo.data.remote

import com.mobileexam.tongo.model.CharacterResponse
import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}
