package com.chentir.eten.di

import com.chentir.data.api.FoursquareAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val FOOD_CATEGORY = "4d4b7105d754a06374d81259"

val networkModule = module {
    single {
        val interceptor = Interceptor {
            val originalRequest = it.request()
            val originalHttpUrl = originalRequest.url()
            val newHttpUrl = originalHttpUrl
                .newBuilder()
                .addQueryParameter("client_id", "C4NTPNVLJUMOA1ZXBSW2YE2HHMZXQMRVGB1GF5SGAXSGE2Y5")
                .addQueryParameter("client_secret", "H2FJEGLRCPEF11I4NI0BXDQBVCRMH1DFKQ5BEIJJMNUZF0IE")
                .addQueryParameter("v", "20201101")
                .addQueryParameter("categoryId", FOOD_CATEGORY)
                .build()
            val requestBuilder = originalRequest.newBuilder().url(newHttpUrl)

            val newRequest = requestBuilder.build()
            it.proceed(newRequest)
        }

        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://api.foursquare.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<FoursquareAPI> {
        val retrofit: Retrofit = get()
        retrofit.create(FoursquareAPI::class.java)
    }
}
