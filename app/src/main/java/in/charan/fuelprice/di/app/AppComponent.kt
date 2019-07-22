package `in`.charan.fuelprice.di.app

import `in`.charan.fuelprice.network.FuelPriceApiService
import androidx.lifecycle.ViewModelProvider
import dagger.Component

/**
Author: Charan Kumar
Date: 2019-07-15
 */
@AppScope
@Component(modules = [FuelPriceApiModule::class, ViewModelFactoryModule::class])
interface AppComponent {

    fun getFuelPriceApiService(): FuelPriceApiService

    fun getViewModelFactory(): ViewModelProvider.Factory
}