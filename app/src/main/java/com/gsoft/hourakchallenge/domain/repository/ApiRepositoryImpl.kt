package com.gsoft.hourakchallenge.domain.repository

import com.gsoft.hourakchallenge.data.datasource.remote.SpotifyApi
import com.gsoft.hourakchallenge.data.model.ArtistResponse
import com.gsoft.hourakchallenge.data.model.TrackResponse
import com.gsoft.hourakchallenge.data.repository.ApiRepository

class ApiRepositoryImpl(
    private val api: SpotifyApi
): ApiRepository {
    override suspend fun getArtists(): List<ArtistResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getArtistTracks(id: String): List<TrackResponse> {
        TODO("Not yet implemented")
    }

}