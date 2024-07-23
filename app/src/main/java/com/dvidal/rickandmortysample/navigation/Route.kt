package com.dvidal.rickandmortysample.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object List: Route

    @Serializable
    data class Details(
        val characterId: Int
    ): Route
}