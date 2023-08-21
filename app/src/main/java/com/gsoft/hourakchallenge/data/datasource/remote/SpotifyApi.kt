package com.gsoft.hourakchallenge.data.datasource.remote

import com.gsoft.hourakchallenge.data.model.ArtistResponse
import com.gsoft.hourakchallenge.data.model.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SpotifyApi {

    @GET("search")
    fun searchArtists(
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): List<ArtistResponse>

    @GET("artists/{id}/top-tracks")
    suspend fun getArtistTopTracks(@Path("id") id: String): List<TrackResponse>

}
