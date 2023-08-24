package com.gsoft.hourakchallenge.data.model

data class SearchResponse(
    val artists: ArtistsContainer
)

data class ArtistsContainer(
    val href: String,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int,
    val items: List<Artist>
)

data class Artist(
    val genres: List<String>,
    val id: String,
    val images: List<Image>,
    val name: String,
    val popularity: Int,
)

data class Image(
    val url: String,
    val height: Int,
    val width: Int
)
