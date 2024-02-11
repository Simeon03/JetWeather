package com.example.jetweather.repos.subrepos

import kotlinx.coroutines.flow.Flow

interface LocationRepo {

    fun fetchCurrentLocation(): Flow<String>

}