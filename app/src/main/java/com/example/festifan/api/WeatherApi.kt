package com.example.festifan.api

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApi {
    companion object {
        private const val baseUrl = "http://weerlive.nl/"

        fun createApi(): WeatherApiService {
            val okHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(ApiInterceptor())
                .build()

            val weatherApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return weatherApi.create(WeatherApiService::class.java)
        }
    }
}

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder().addQueryParameter("key", "815777113a").build()
        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}