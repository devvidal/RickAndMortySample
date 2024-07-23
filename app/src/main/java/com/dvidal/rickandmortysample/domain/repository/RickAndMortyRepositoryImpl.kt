package com.dvidal.rickandmortysample.domain.repository

import com.dvidal.rickandmortysample.data.local.RickAndMortyDao
import com.dvidal.rickandmortysample.data.mapToDomain
import com.dvidal.rickandmortysample.data.mapToEntity
import com.dvidal.rickandmortysample.data.network.RickAndMortyApi
import com.dvidal.rickandmortysample.model.Character
import com.dvidal.rickandmortysample.model.CharacterPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RickAndMortyRepositoryImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyDao: RickAndMortyDao
): RickAndMortyRepository {

    override suspend fun fetchCharacters(page: Int): Flow<List<CharacterPreview>> {
        val apiResults = rickAndMortyApi.fetchCharacters(page).map { it.mapToEntity() }
        rickAndMortyDao.insertAllCharacters(apiResults)

        return rickAndMortyDao.fetchAllCharacters().map { it.map { entities -> entities.mapToDomain() } }
    }

    override suspend fun fetchCharacter(id: Int): Character {
        return rickAndMortyApi.fetchCharacter(id)
    }
}