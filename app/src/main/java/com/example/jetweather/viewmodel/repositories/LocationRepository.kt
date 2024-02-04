package com.example.jetweather.viewmodel.repositories

import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun fetchLocationText(): Flow<String>

}