package com.example.jetweather.di

import android.content.Context
import com.example.jetweather.constants.Api.OPEN_METEO_BASE_URL
import com.example.jetweather.constants.Api.TOM_TOM_BASE_URL
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.model.RetrofitInstance
import com.example.jetweather.model.TomTom
import com.example.jetweather.repos.sub.DefaultHourlyWeatherRepository
import com.example.jetweather.repos.sub.DefaultLocationRepository
import com.example.jetweather.repos.sub.DefaultWeeklyWeatherRepository
import com.example.jetweather.repos.sub.HourlyWeatherRepo
import com.example.jetweather.repos.sub.LocationRepo
import com.example.jetweather.repos.sub.WeeklyWeatherRepo
import com.example.jetweather.viewmodel.CurrentLocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Scope for the entire application
object WeatherModule {

    @Provides
    @Singleton
    fun provideOpenMeteo(): OpenMeteo {
        return RetrofitInstance.get(OPEN_METEO_BASE_URL).create(OpenMeteo::class.java)
    }

    @Provides
    @Singleton
    fun provideTomTom(): TomTom {
        return RetrofitInstance.get(TOM_TOM_BASE_URL).create(TomTom::class.java)
    }

    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

}

@Module
@InstallIn(ActivityComponent::class) // ActivityComponent is used for Activity lifecycle-scoped dependencies
object LocationModule {

    // Provide LocationRepo Implementation
    @Provides
    fun provideDefaultLocationRepository(
        @ActivityContext context: Context,
        fusedLocationProviderClient: FusedLocationProviderClient
    ): LocationRepo {
        return DefaultLocationRepository(context, fusedLocationProviderClient)
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class) // Matches the lifecycle of ViewModel
abstract class ImplementationModule {

    @Binds
    abstract fun bindLocationRepo(
        defaultLocationRepository: DefaultLocationRepository
    ): LocationRepo

    @Binds
    abstract fun bindHourlyWeatherRepo(
        defaultHourlyWeatherRepository: DefaultHourlyWeatherRepository
    ): HourlyWeatherRepo

    @Binds
    abstract fun bindWeeklyWeatherRepo(
        defaultWeeklyWeatherRepository: DefaultWeeklyWeatherRepository
    ): WeeklyWeatherRepo
}

@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {

    @Provides
    fun provideCurrentLocationViewModel(
        locationRepo: LocationRepo
    ): CurrentLocationViewModel {
        return CurrentLocationViewModel(locationRepo)
    }
}