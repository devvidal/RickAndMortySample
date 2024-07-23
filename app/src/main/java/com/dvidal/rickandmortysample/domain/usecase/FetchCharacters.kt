package com.dvidal.rickandmortysample.domain.usecase

import com.dvidal.rickandmortysample.domain.repository.RickAndMortyRepository
import com.dvidal.rickandmortysample.model.CharacterPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCharacters @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {

    suspend fun invoke(page: Int): Flow<List<CharacterPreview>> {
        return rickAndMortyRepository.fetchCharacters(page)
    }
}