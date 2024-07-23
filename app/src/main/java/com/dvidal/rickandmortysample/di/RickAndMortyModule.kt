package com.dvidal.rickandmortysample.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.dvidal.rickandmortysample.data.local.RickAndMortyDao
import com.dvidal.rickandmortysample.data.local.RickAndMortyDatabase
import com.dvidal.rickandmortysample.data.network.RickAndMortyApi
import com.dvidal.rickandmortysample.data.network.RickAndMortyApiImpl
import com.dvidal.rickandmortysample.domain.repository.RickAndMortyRepository
import com.dvidal.rickandmortysample.domain.repository.RickAndMortyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RickAndMortyModule {

    @Provides
    fun provideApolloClient(): ApolloClient {
        return  ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }


    @Provides
    fun provideRickAndMortyApi(apolloClient: ApolloClient): RickAndMortyApi {
        return RickAndMortyApiImpl(apolloClient)
    }

    @Provides
    fun providesRickAndMortyDatabase(
        @ApplicationContext applicationContext: Context
    ): RickAndMortyDatabase {
        return Room.databaseBuilder(
            applicationContext,
            RickAndMortyDatabase::class.java, "rickAndMortyDatabase"
        ).build()
    }

    @Provides
    fun providesRickAndMortyDao(database: RickAndMortyDatabase): RickAndMortyDao {
        return database.dao()
    }

    @Provides
    fun provideRickAndMortyRepository(
        rickAndMortyApi: RickAndMortyApiImpl,
        rickAndMortyDao: RickAndMortyDao
    ): RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(
            rickAndMortyApi = rickAndMortyApi,
            rickAndMortyDao = rickAndMortyDao
        )
    }
}