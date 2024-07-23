package com.dvidal.rickandmortysample.data

import com.dvidal.rickandmortysample.FetchCharacterQuery
import com.dvidal.rickandmortysample.FetchCharactersQuery
import com.dvidal.rickandmortysample.data.local.CharacterEntity
import com.dvidal.rickandmortysample.model.Character
import com.dvidal.rickandmortysample.model.CharacterPreview

fun FetchCharactersQuery.Data?.mapToDomain(): List<CharacterPreview> {
    return this?.characters?.results?.mapNotNull {
        CharacterPreview(
            id = it?.id?.toInt() ?: -1,
            name = it?.name.orEmpty(),
            species = it?.species.orEmpty(),
            image = it?.image.orEmpty()
        )
    } ?: emptyList()
}

fun CharacterEntity.mapToDomain(): CharacterPreview {
    return CharacterPreview(
        id = id,
        name = name,
        species = species,
        image = image
    )
}

fun CharacterPreview.mapToEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        species = species,
        image = image
    )
}

fun FetchCharacterQuery.Data.mapToDomain(): Character {
    val character = this.character

    return Character(
        id = character?.id?.toInt() ?: -1,
        name = character?.name.orEmpty(),
        status = character?.status.orEmpty(),
        species = character?.species.orEmpty(),
        type = character?.type.orEmpty(),
        gender = character?.gender.orEmpty(),
        image = character?.image.orEmpty(),
    )
}