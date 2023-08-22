package com.gsoft.hourakchallenge.di


import com.gsoft.hourakchallenge.data.datasource.remote.SpotifyAuthService
import com.gsoft.hourakchallenge.util.Contants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthClientDependencies {
    companion object {
        private const val authUrl =  Contants.BASE_AUTH_URL
    }


    @Provides
    @Singleton
    @Named("auth")
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }



    @Singleton
    @Provides
    @Named("auth")
    fun provideRetrofit(@Named("auth")okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(authUrl)
        .client(okHttpClient)
        .build()



    @Singleton
    @Provides
    @Named("auth")
    fun provideAuthService(@Named("auth")retrofit: Retrofit): SpotifyAuthService = retrofit.create(SpotifyAuthService::class.java)


}