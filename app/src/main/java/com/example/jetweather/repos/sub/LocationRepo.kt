package com.example.jetweather.repos.sub

import kotlinx.coroutines.flow.Flow

interface LocationRepo {

    fun fetchCurrentLocation(): Flow<String>

}