package com.mobileexam.tongo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileexam.tongo.data.repository.CharacterRepository
import com.mobileexam.tongo.model.Character
import com.mobileexam.tongo.model.CharacterEntity
import com.mobileexam.tongo.model.Location
import com.mobileexam.tongo.model.Origin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


sealed class CharacterState {
    data object Loading : CharacterState()
    data class Success(val characters: List<Character>) : CharacterState()
    data class Error(val message: String) : CharacterState()
}

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {
    private val _state = MutableStateFlow<CharacterState>(CharacterState.Loading)
    val state: StateFlow<CharacterState> = _state.asStateFlow()

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            repository.fetchCharactersFromApi()

            repository.getCharacters().collectLatest { entityList: List<CharacterEntity> ->
            _state.value = CharacterState.Success(entityList.map { entity: CharacterEntity ->
            Character(
                        id = entity.id,
                        name = entity.name,
                        status = entity.status,
                        species = entity.species,
                        type = entity.type,
                        gender = entity.gender,
                        image = entity.image,
                        location = Location(entity.location),
                        origin = Origin(entity.origin)
                    )
                })
            }
        }
    }
}
