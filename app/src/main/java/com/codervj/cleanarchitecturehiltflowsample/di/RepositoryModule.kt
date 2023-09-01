package com.codervj.cleanarchitecturehiltflowsample.di

import com.codervj.cleanarchitecturehiltflowsample.data.DataRepository
import com.codervj.cleanarchitecturehiltflowsample.data.DataRepositoryImpl
import com.codervj.network.APIInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDataRepository(apiInterface: APIInterface) : DataRepository {
        return DataRepositoryImpl(apiInterface)
    }

}