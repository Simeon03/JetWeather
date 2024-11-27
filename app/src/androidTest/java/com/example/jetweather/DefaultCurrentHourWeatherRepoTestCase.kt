package com.example.jetweather

import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.DefaultWeatherRepo
import com.example.jetweather.repos.sub.DefaultCurrentHourWeatherRepo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DefaultCurrentHourWeatherRepoTestCase {

    private lateinit var weatherApi: OpenMeteo
    private lateinit var weatherRepo: DefaultWeatherRepo
    private lateinit var currentHourWeatherRepo: DefaultCurrentHourWeatherRepo

    @Before
    fun setUp() {
        weatherApi = mock(OpenMeteo::class.java)
        weatherRepo = mock(DefaultWeatherRepo::class.java)
        currentHourWeatherRepo = DefaultCurrentHourWeatherRepo(weatherApi, weatherRepo)
    }

    @Test
    fun testFetchCloudCoverReturnsCorrectValue() = runTest {
        // Mock the response from the weatherRepo.handleResponse
        val mockResponse = flow { emit(75) }
        whenever(weatherRepo.handleResponse(any(), any(), any())).thenReturn(mockResponse)

        // Call the method under test
        val result = currentHourWeatherRepo.fetchCloudCover().first()

        // Assert that the result is correct
        assertEquals(75, result)
    }

    @Test
    fun testFetchVisibilityReturnsCorrectValue() = runTest {
        // Mock the response from the weatherRepo.handleResponse
        val mockResponse = flow { emit(10000) }
        whenever(weatherRepo.handleResponse(any(), any(), any())).thenReturn(mockResponse)

        // Call the method under test
        val result = currentHourWeatherRepo.fetchVisibility().first()

        // Assert that the result is correct
        assertEquals(10000, result)
    }

    @Test
    fun testFetchUvIndexReturnsCorrectValue() = runTest {
        // Mock the response from the weatherRepo.handleResponse
        val mockResponse = flow { emit(5.5f) }
        whenever(weatherRepo.handleResponse(any(), any(), any())).thenReturn(mockResponse)

        // Call the method under test
        val result = currentHourWeatherRepo.fetchUvIndex().first()

        // Assert that the result is correct
        assertEquals(5.5f, result, 0.0f)
    }
}
}