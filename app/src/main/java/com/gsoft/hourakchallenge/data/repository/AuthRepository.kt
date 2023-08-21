package com.gsoft.hourakchallenge.data.repository

interface AuthRepository {
    suspend fun fetchAccessToken(credentialsBase64: String): String?
    suspend fun isAuth(): Boolean
}