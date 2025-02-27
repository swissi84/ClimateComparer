package de.syntax_institut.jetpack.ClimateComparer


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntax_institut.jetpack.ClimateComparer.data.Remote.api.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL2 = "https://api.open-meteo.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val loggingInterceptor = HttpLoggingInterceptor().apply {
    // Logging Levels: BODY, BASIC, NONE, HEADERS
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okHttpClient)
    .baseUrl(BASE_URL2)
    .build()


interface GetWeather {
    @GET("forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m,relative_humidity_2m,weather_code",
        @Query("forecast_days") forecastDays: Int = 7,


        ): WeatherResponse
}

object WeatherApi {
    val retrofitService: GetWeather by lazy { retrofit.create(GetWeather::class.java) }
}



