package com.gsoft.hourakchallenge.data.repository

import com.gsoft.hourakchallenge.data.model.Artist
import com.gsoft.hourakchallenge.data.model.SearchResponse
import com.gsoft.hourakchallenge.data.model.TracksResponse
import com.gsoft.hourakchallenge.util.MyResource
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    suspend fun getArtists(query: String): Flow<MyResource<SearchResponse?>>

    suspend fun getArtist(id: String): Flow<MyResource<Artist?>>

    suspend fun getArtistTracks(id: String): Flow<MyResource<TracksResponse?>>
}