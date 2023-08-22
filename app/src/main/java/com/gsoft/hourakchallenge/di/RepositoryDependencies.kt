package com.gsoft.hourakchallenge.di

import com.gsoft.hourakchallenge.data.datasource.remote.SpotifyApi
import com.gsoft.hourakchallenge.data.datasource.remote.SpotifyAuthService
import com.gsoft.hourakchallenge.data.repository.ApiRepository
import com.gsoft.hourakchallenge.data.repository.AuthRepository
import com.gsoft.hourakchallenge.domain.repository.ApiRepositoryImpl
import com.gsoft.hourakchallenge.domain.repository.AuthRepositoryImpl
import com.gsoft.hourakchallenge.util.SharePreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryDependencies {

    @Provides
    @Singleton
    @Named("api")
    fun provideApiRepository(
        @Named("api")api: SpotifyApi
    ): ApiRepository {
        return ApiRepositoryImpl(
            api = api,
        )
    }


    @Provides
    @Singleton
    @Named("auth")
    fun provideAuthRepository(
        @Named("auth")api: SpotifyAuthService,
        sharedPreference : SharePreferencesManager
    ): AuthRepository {
        return AuthRepositoryImpl(
            authApi = api,
            sharePreferencesManager = sharedPreference
        )
    }

}