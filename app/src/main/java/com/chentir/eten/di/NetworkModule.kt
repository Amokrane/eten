package com.chentir.eten.di

import android.content.Context
import com.chentir.data.api.FoursquareAPI
import com.chentir.eten.R
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
            val context: Context = get()

            val newHttpUrl = originalHttpUrl
                .newBuilder()
                .addQueryParameter("client_id", context.getString(R.string.foursquare_client_id))
                .addQueryParameter("client_secret", context.getString(R.string.foursquare_client_secret))
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
