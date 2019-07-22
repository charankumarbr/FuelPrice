package `in`.charan.fuelprice

import `in`.charan.fuelprice.di.app.AppComponent
import `in`.charan.fuelprice.di.app.ContextModule
import `in`.charan.fuelprice.di.app.DaggerAppComponent
import android.app.Application
import android.util.Log

/**
Author: Charan Kumar
Date: 2019-07-15
 */
class FuelPriceApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()

        Log.d("Application", "1. ${appComponent.getFuelPriceApiService()}")
        Log.d("Application", "2. ${appComponent.getFuelPriceApiService()}")
        Log.d("Application", "3. ${appComponent.getFuelPriceApiService()}")

    }

    fun getAppComponent() = appComponent
}