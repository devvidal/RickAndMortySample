package com.dvidal.rickandmortysample.data.network

import com.dvidal.rickandmortysample.model.Character
import com.dvidal.rickandmortysample.model.CharacterPreview

interface RickAndMortyApi {

    suspend fun fetchCharacters(page: Int = 1): List<CharacterPreview>

    suspend fun fetchCharacter(id: Int): Character
}