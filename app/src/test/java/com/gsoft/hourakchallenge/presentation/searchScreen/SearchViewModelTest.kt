package com.gsoft.hourakchallenge.presentation.searchScreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gsoft.hourakchallenge.MainCoroutineRule
import com.gsoft.hourakchallenge.data.model.ArtistsContainer
import com.gsoft.hourakchallenge.data.model.SearchResponse
import com.gsoft.hourakchallenge.domain.usecase.SearchArtistsUsecase
import com.gsoft.hourakchallenge.util.MyResource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
internal class SearchViewModelTest{

    @RelaxedMockK
    private lateinit var getArtistsUsecase: SearchArtistsUsecase


    private lateinit var searchViewModel: SearchViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        searchViewModel = SearchViewModel(getArtistsUsecase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when useCase return a list of artists set on the flow`() = runTest {
        //Given
        //
        val artistsContainer = ArtistsContainer(href = "some", items = listOf(), total = 1, limit = 1, offset = 1, next = null, previous = null)
        val searchResponse  = SearchResponse(artistsContainer)
        val result = MyResource.Success(searchResponse)

        coEvery { getArtistsUsecase.invoke("query") } returns flowOf(result)

        //When
        searchViewModel.fetchData("query")

        //Then
        advanceUntilIdle()
        assert(searchViewModel.feedState.feedDate == result.data.artists.items)
    }


    @Test
    fun `when useCase return Failure`() = runTest {
        //Given
        val response  = Exception("some")
        val result = MyResource.Failure(response)

        coEvery { getArtistsUsecase.invoke("query") } returns flowOf(result)

        //When
        searchViewModel.fetchData("query")

        //Then
        advanceUntilIdle()
        assert(searchViewModel.feedState.error)
        assert(searchViewModel.feedState.message == "some")
    }

}