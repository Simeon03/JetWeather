package com.example.jetweather.repos

import com.example.jetweather.repos.subrepos.CurrentWeatherRepo
import com.example.jetweather.repos.subrepos.HourlyWeatherRepo
import com.example.jetweather.repos.subrepos.LocationRepo
import com.example.jetweather.repos.subrepos.WeeklyWeatherRepo

interface AllRepos :
    CurrentWeatherRepo,
    WeeklyWeatherRepo,
    HourlyWeatherRepo,
    LocationRepo