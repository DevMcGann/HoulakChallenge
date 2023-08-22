package com.gsoft.hourakchallenge.domain.usecase

import com.gsoft.hourakchallenge.data.repository.AuthRepository
import com.gsoft.hourakchallenge.util.Contants
import javax.inject.Inject
import javax.inject.Named

class getTokenUsecase @Inject constructor(
    @Named("auth")private val authRepository: AuthRepository
    ) {
    suspend operator fun invoke() : String? {
        val credentialsBase64: String = android.util.Base64.encodeToString(Contants.CREDENTIALS.toByteArray(), android.util.Base64.NO_WRAP)
        return authRepository.fetchAccessToken(credentialsBase64)
    }
}

