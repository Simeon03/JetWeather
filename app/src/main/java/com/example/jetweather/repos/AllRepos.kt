package com.example.jetweather.repos

import com.example.jetweather.repos.sub.CurrentWeatherRepo
import com.example.jetweather.repos.sub.HourlyWeatherRepo
import com.example.jetweather.repos.sub.LocationRepo
import com.example.jetweather.repos.sub.WeeklyWeatherRepo

interface AllRepos :
    CurrentWeatherRepo,
    WeeklyWeatherRepo,
    HourlyWeatherRepo,
    LocationRepo