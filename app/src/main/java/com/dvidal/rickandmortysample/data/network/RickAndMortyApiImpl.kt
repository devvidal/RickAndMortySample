package com.dvidal.rickandmortysample.data.network

import com.apollographql.apollo3.ApolloClient
import com.dvidal.rickandmortysample.FetchCharacterQuery
import com.dvidal.rickandmortysample.FetchCharactersQuery
import com.dvidal.rickandmortysample.data.mapToDomain
import com.dvidal.rickandmortysample.model.Character
import com.dvidal.rickandmortysample.model.CharacterPreview
import javax.inject.Inject

class RickAndMortyApiImpl @Inject constructor(
    private val apolloClient: ApolloClient
): RickAndMortyApi {

    override suspend fun fetchCharacters(page: Int): List<CharacterPreview> {
        val query = FetchCharactersQuery(page = page)
        return apolloClient.query(query).execute().data?.mapToDomain()  ?: emptyList()
    }

    override suspend fun fetchCharacter(id: Int): Character {
        val query = FetchCharacterQuery(id = id.toString())
        return apolloClient.query(query).execute().data?.mapToDomain() ?: Character()
    }
}