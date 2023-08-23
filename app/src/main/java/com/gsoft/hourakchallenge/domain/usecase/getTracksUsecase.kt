package com.gsoft.hourakchallenge.domain.usecase

import com.gsoft.hourakchallenge.data.model.TracksResponse
import com.gsoft.hourakchallenge.data.repository.ApiRepository
import com.gsoft.hourakchallenge.util.MyResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class getTracksUsecase @Inject constructor (
    @Named("api")private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(id: String) : Flow<MyResource<TracksResponse?>> {
        return  apiRepository.getArtistTracks(id)
    }
}