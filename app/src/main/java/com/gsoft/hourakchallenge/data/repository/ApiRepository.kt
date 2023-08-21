package com.gsoft.hourakchallenge.data.repository

import com.gsoft.hourakchallenge.data.model.ArtistResponse
import com.gsoft.hourakchallenge.data.model.TrackResponse

interface ApiRepository {
    suspend fun getArtists(): List<ArtistResponse>
    suspend fun getArtistTracks(id: String): List<TrackResponse>
}