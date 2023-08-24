package com.gsoft.hourakchallenge.presentation.splashScreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gsoft.hourakchallenge.MainCoroutineRule
import com.gsoft.hourakchallenge.domain.usecase.getTokenUsecase
import com.gsoft.hourakchallenge.util.SharePreferencesManager
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SplashViewModelTest {

    @RelaxedMockK
    private lateinit var getTokenUsecase: getTokenUsecase

    @RelaxedMockK
    private lateinit var sharedPreferencesManager: SharePreferencesManager

    private lateinit var authViewModel: SplashViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        authViewModel = SplashViewModel(getTokenUsecase, sharedPreferencesManager)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when useCase return a valid token then state turns true`() = runTest {
        //Given
        val token = "token"

        coEvery { getTokenUsecase.invoke() } returns token

        //When
        authViewModel.getToken()

        //Then
        advanceUntilIdle()
        assert(authViewModel.state.value.isAuth) { true }
        assert(authViewModel.state.value.isError) { false }
    }

}
