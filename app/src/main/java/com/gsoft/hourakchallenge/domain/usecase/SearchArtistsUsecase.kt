package com.gsoft.hourakchallenge.domain.usecase

import com.gsoft.hourakchallenge.data.model.SearchResponse
import com.gsoft.hourakchallenge.data.repository.ApiRepository
import com.gsoft.hourakchallenge.util.MyResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class SearchArtistsUsecase @Inject constructor (
    @Named("api")private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(query: String) : Flow<MyResource<SearchResponse?>> {
        return  apiRepository.getArtists(query)
    }
}