package `in`.charan.fuelprice.di.app

import `in`.charan.fuelprice.data.PrefRepo
import `in`.charan.fuelprice.repo.FuelPriceRepo
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

/**
Author: Charan Kumar
Date: 2019-07-15
 */
@Module(includes = [PrefModule::class, FuelPriceApiModule::class])
class ViewModelFactoryModule {

    @Provides
    @AppScope
    fun viewModelFactory(prefRepo: PrefRepo, fuelPriceRepo: FuelPriceRepo): ViewModelProvider.Factory =
        FuelPriceViewModelFactory(prefRepo, fuelPriceRepo)
}