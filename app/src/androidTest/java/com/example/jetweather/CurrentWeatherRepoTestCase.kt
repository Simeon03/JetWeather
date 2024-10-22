package com.example.jetweather

import com.example.jetweather.repos.sub.DefaultCurrentWeatherRepository
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNotSame
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CurrentWeatherRepoTestCase {

    private lateinit var currentWeatherRepo: DefaultCurrentWeatherRepository
//    private lateinit var weatherApi: OpenMeteo

    @Before
    fun setUp() {
//        weatherApi = RetrofitInstance.get(Api.OPEN_METEO_BASE_URL).create(OpenMeteo::class.java)
//        currentWeatherRepo = DefaultCurrentWeatherRepository(weatherApi)
    }

    @Test
    fun testFetchTemp() = runBlocking {
        val temp = currentWeatherRepo.fetchTemp().first()
        assertNotSame(0, temp)
        assertNotNull(temp)
    }

}