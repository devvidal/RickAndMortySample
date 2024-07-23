package com.dvidal.rickandmortysample.domain.repository

import com.dvidal.rickandmortysample.model.Character
import com.dvidal.rickandmortysample.model.CharacterPreview
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {

    suspend fun fetchCharacters(page: Int = 1): Flow<List<CharacterPreview>>

    suspend fun fetchCharacter(id: Int): Character
}