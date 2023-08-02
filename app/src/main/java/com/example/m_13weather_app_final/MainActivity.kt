package com.example.m_13weather_app_final

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

class MainActivity : ComponentActivity() {


    var tv_temp: TextView? = null
    var tv_name: TextView? = null
    var weathericon: ImageView? = null
    var wdescription: TextView? = null
    var feelslike:TextView?=null
    var wtemp_max:TextView?= null
    var wtemp_min:TextView?= null
    var humidity:TextView?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main)

        val result = findViewById<TextView>(R.id.result)

        val sharedPreferences = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)


        val email = sharedPreferences.getString("EMAIL", "").toString()
        result.text = "$email"

        tv_temp = findViewById(R.id.tv_temp)
        tv_name=findViewById(R.id.tv_name)
        weathericon=findViewById(R.id.weathericon)
        wdescription=findViewById(R.id.wdescription)
        feelslike=findViewById(R.id.feels_like)
        wtemp_max=findViewById(R.id.tamp_max)
        wtemp_min=findViewById(R.id.temp_min)
        humidity = findViewById(R.id.humidity)


        fetchWeatherData()

    }

    private fun fetchWeatherData(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val response= retrofit.getWeatherData("Muscat", "6521ce43a12507792ef9884227b444d8", "metric")

        response.enqueue(object :Callback<WeatherApi>{
            override fun onResponse(call: Call<WeatherApi>, response: Response<WeatherApi>) {
                val responseBody = response.body()!!

                tv_temp!!.text = responseBody.list[2].main.temp.toString()
                tv_name!!.text= responseBody.city.name
                wdescription!!.text= responseBody.list[0].weather[0].description.toString()
                feelslike!!.text=responseBody.list[2].main.feels_like.toString()
                wtemp_max!!.text=responseBody.list[2].main.temp_max.toString()
                wtemp_min!!.text=responseBody.list[2].main.temp_min.toString()
                humidity!!.text = responseBody.list[2].main.humidity.toString()



                Glide.with(applicationContext)
                    .load("https://openweathermap.org/img/w/"+(responseBody.list[0].weather[0].icon).toString()+".png")
                    .into(weathericon!!)

            }

            override fun onFailure(call: Call<WeatherApi>, t: Throwable) {
                Log.d("DATA", t.toString())
            }


        })
    }
}
