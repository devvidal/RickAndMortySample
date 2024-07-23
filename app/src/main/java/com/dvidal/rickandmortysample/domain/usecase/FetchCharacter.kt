package com.dvidal.rickandmortysample.domain.usecase

import com.dvidal.rickandmortysample.domain.repository.RickAndMortyRepository
import com.dvidal.rickandmortysample.model.Character
import javax.inject.Inject

class FetchCharacter @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {

    suspend operator fun invoke(id: Int): Character {
        return rickAndMortyRepository.fetchCharacter(id)
    }
}