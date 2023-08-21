package com.gsoft.hourakchallenge.data.datasource.remote

import com.gsoft.hourakchallenge.util.Contants
import com.gsoft.hourakchallenge.util.SharePreferencesManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
        private val prefs: SharePreferencesManager
) : Interceptor {

    private lateinit var token: String

    private lateinit var response : Response

    override fun intercept(chain: Interceptor.Chain): Response {
        token = prefs.getString(Contants.KEY_SHARED_PREFERENCE, "")!!

        var request = chain.request()

        if(request.header("No-Authentication") == null && token.isNotEmpty()){
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }

        response = chain.proceed(request)

        return response
    }
}