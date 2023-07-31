package com.example.m_13weather_app_final

data class WeatherApi(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DT>,
    val message: Int
)