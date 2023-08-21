package com.gsoft.hourakchallenge.domain.repository

import android.util.Log
import com.gsoft.hourakchallenge.data.datasource.remote.SpotifyAuthService
import com.gsoft.hourakchallenge.data.repository.AuthRepository
import com.gsoft.hourakchallenge.util.Contants
import com.gsoft.hourakchallenge.util.SharePreferencesManager


class AuthRepositoryImpl  (
    private val authApi: SpotifyAuthService,
    private val sharePreferencesManager: SharePreferencesManager
        ): AuthRepository {

    override suspend fun fetchAccessToken(credentialsBase64: String): String? {
        val response = authApi.getAccessToken(
            authorization = "Basic $credentialsBase64",
            grantType = "client_credentials"
        )

        if (response.isSuccessful) {
            Log.d("TOKEEEN", "fetchAccessToken: ${response.body()}  response= ${response.code()}")
            return response.body()?.accessToken
        }
        return null
    }


    override suspend fun isAuth(): Boolean {
        return sharePreferencesManager.getString(Contants.KEY_SHARED_PREFERENCE, null) != null
    }
}