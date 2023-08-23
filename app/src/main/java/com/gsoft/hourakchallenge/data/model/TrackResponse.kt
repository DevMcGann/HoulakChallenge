package com.gsoft.hourakchallenge.data.model


data class TracksResponse(
    val tracks: List<Track>
)

data class Track(
    val id: String,
    val name: String,
    val popularity: Int,
)





