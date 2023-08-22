package com.gsoft.hourakchallenge.domain.repository

import android.util.Log
import com.gsoft.hourakchallenge.data.datasource.remote.SpotifyApi
import com.gsoft.hourakchallenge.data.model.SearchResponse
import com.gsoft.hourakchallenge.data.model.TrackResponse
import com.gsoft.hourakchallenge.data.repository.ApiRepository
import com.gsoft.hourakchallenge.util.Contants
import com.gsoft.hourakchallenge.util.MyResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Named

class ApiRepositoryImpl @Inject constructor(
    @Named("api") private val api: SpotifyApi
): ApiRepository {
    override suspend fun getArtists(query: String): Flow<MyResource<SearchResponse?>> {
            return try {
                val response = api.searchArtists(query, Contants.API_TYPE, Contants.API_LIMIT_RESPONSE, Contants.API_OFFSET_RESPONSE)
                if (response.isSuccessful) {
                    val searchResponse = response.body()
                    flowOf(MyResource.Success(searchResponse))
                } else {
                    flowOf(MyResource.Failure(Exception("Request failed with code ${response.code()}")))
                }
            } catch (e: Exception) {
                flowOf(MyResource.Failure(e))
            }
        }

    override suspend fun getArtistTracks(id: String): Flow<MyResource<List<TrackResponse>>> {
        return  try {
            flowOf(MyResource.Success(api.getArtistTopTracks(id)))
        } catch (e: Exception) {
            flowOf(MyResource.Failure(e))
        }
    }
}

