package com.gsoft.hourakchallenge.presentation.detailScreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gsoft.hourakchallenge.MainCoroutineRule
import com.gsoft.hourakchallenge.data.model.Artist
import com.gsoft.hourakchallenge.data.model.Image
import com.gsoft.hourakchallenge.data.model.TracksResponse
import com.gsoft.hourakchallenge.domain.usecase.getArtistUsecase
import com.gsoft.hourakchallenge.domain.usecase.getTracksUsecase
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
internal class DetailViewModelTest{
    @RelaxedMockK
    private lateinit var getArtistUsecase: getArtistUsecase

    @RelaxedMockK
    private lateinit var getTracksUsecase: getTracksUsecase


    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        detailViewModel = DetailViewModel(getArtistUsecase, getTracksUsecase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getArtistUsecase return an artist on the flow`() = runTest {
        //Given
        val artist = Artist(
            genres = listOf("some"),
            id = "id",
            name = "name",
            popularity = 1,
            images = listOf(Image(height = 1, width = 1, url = "some"))
        )
        val result = MyResource.Success(artist)

        coEvery { getArtistUsecase.invoke("id") } returns flowOf(result)

        //When
        detailViewModel.fetchData("id")

        //Then
        advanceUntilIdle()
        assert(detailViewModel.detailState.artistData == result.data)
    }

    @Test
    fun `when getArtistUsecase return Failure`() = runTest {
        //Given
        val response  = Exception("some")
        val result = MyResource.Failure(response)

        coEvery { getArtistUsecase.invoke("query") } returns flowOf(result)

        //When
        detailViewModel.fetchData("query")

        //Then
        advanceUntilIdle()
        assert(detailViewModel.detailState.error)
        assert(detailViewModel.detailState.message == "some")
    }

    @Test
    fun `when trackUsecase return a list of artists set on the flow`() = runTest {
        //Given
        //
        val trackResponse = TracksResponse(tracks = listOf())
        val result = MyResource.Success(trackResponse)

        coEvery { getTracksUsecase.invoke("id") } returns flowOf(result)

        //When
        detailViewModel.fetchSongs("id")

        //Then
        advanceUntilIdle()
        assert(detailViewModel.detailState.tracks == result.data.tracks)
    }

    @Test
    fun `when trackUsecase return Failure`() = runTest {
        //Given
        val response  = Exception("some")
        val result = MyResource.Failure(response)

        coEvery { getTracksUsecase.invoke("query") } returns flowOf(result)

        //When
        detailViewModel.fetchSongs("query")

        //Then
        advanceUntilIdle()
        assert(detailViewModel.detailState.error)
        assert(detailViewModel.detailState.message == "some")
    }
}