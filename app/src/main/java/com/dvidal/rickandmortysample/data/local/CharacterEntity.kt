package com.dvidal.rickandmortysample.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Int = -1,
    val name: String = "",
    val species: String = "",
    val image: String = ""
)