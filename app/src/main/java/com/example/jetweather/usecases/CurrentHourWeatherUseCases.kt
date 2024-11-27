package com.example.jetweather.usecases

import com.example.jetweather.repos.sub.DefaultCurrentHourWeatherRepo
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FetchCloudCoverUseCase @Inject constructor(
    private val weatherRepo: DefaultCurrentHourWeatherRepo
) {
    suspend operator fun invoke(): Int {
        return weatherRepo.fetchCloudCover().first()
    }
}

class FetchVisibilityUseCase @Inject constructor(
    private val weatherRepo: DefaultCurrentHourWeatherRepo
) {
    suspend operator fun invoke(): Int {
        return weatherRepo.fetchVisibility().first()
    }
}

class FetchUvIndexUseCase @Inject constructor(
    private val weatherRepo: DefaultCurrentHourWeatherRepo
) {
    suspend operator fun invoke(): Float {
        return weatherRepo.fetchUvIndex().first()
    }
}
