package com.gsoft.hourakchallenge.di

import com.gsoft.hourakchallenge.data.datasource.remote.SpotifyApi
import com.gsoft.hourakchallenge.data.datasource.remote.TokenInterceptor
import com.gsoft.hourakchallenge.util.Contants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SpotifyAPIDependencies {
    companion object {
        private const val apiBaseUrl =  Contants.BASE_URL
    }

    @Provides
    @Singleton
    @ApiService
    fun provideOkHttpClient(
        tokenInterceptor: TokenInterceptor,
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(tokenInterceptor)
        return okHttpClientBuilder.build()

    }

    @Provides
    @Singleton
    @ApiService
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }



    @Singleton
    @Provides
    @ApiService
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(apiBaseUrl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    @ApiService
    fun provideApiService(retrofit: Retrofit): SpotifyApi = retrofit.create(SpotifyApi::class.java)


}