package com.chentir.eten.di

import com.chentir.data.services.SearchVenuesService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val NetworkModule = module {
    single {
        val authInterceptor = Interceptor {
            val originalRequest = it.request()
            val originalHttpUrl = originalRequest.url()
            val newHttpUrl = originalHttpUrl.newBuilder().addQueryParameter("client_id", "foo")
                .addQueryParameter("client_secret", "bar").build()
            val requestBuilder = originalRequest.newBuilder().url(newHttpUrl)

            val newRequest = requestBuilder.build()
            it.proceed(newRequest)
        }

        OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    single {
        Retrofit.Builder().baseUrl("https://api.foursquare.com/v2/venues/").client(get())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    single<SearchVenuesService> {
        val retrofit: Retrofit = get()
        retrofit.create(SearchVenuesService::class.java)
    }
}
