package com.chentir.eten

import android.app.Application
import com.chentir.detail.di.restaurantDetailModule
import com.chentir.eten.di.nearestRestaurantsUseCasesModule
import com.chentir.eten.di.networkModule
import com.chentir.maps.di.restaurantMapsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EtenApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@EtenApp)
            modules(
                listOf(
                    networkModule,
                    restaurantMapsModule,
                    restaurantDetailModule,
                    nearestRestaurantsUseCasesModule
                )
            )
        }
    }
}
