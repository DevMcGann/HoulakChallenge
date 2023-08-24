package com.gsoft.hourakchallenge.data.datasource.remote

import com.gsoft.hourakchallenge.data.model.Artist
import com.gsoft.hourakchallenge.data.model.SearchResponse
import com.gsoft.hourakchallenge.data.model.TracksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SpotifyApi {

    @GET("search")
    suspend fun searchArtists(
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<SearchResponse>


    @GET("artists/{id}")
    suspend fun getArtist(@Path("id") id: String): Response<Artist>


    @GET("artists/{id}/top-tracks")
    suspend fun getArtistTopTracks(@Path("id") id: String,  @Query("market") market: String): Response<TracksResponse>

}

