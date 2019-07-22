package `in`.charan.fuelprice.di.app

import `in`.charan.fuelprice.data.PrefRepo
import `in`.charan.fuelprice.repo.FuelPriceRepo
import `in`.charan.fuelprice.ui.choosestate.ChooseStateViewModel
import `in`.charan.fuelprice.ui.home.BrandViewModel
import `in`.charan.fuelprice.ui.home.HomeViewModel
import `in`.charan.fuelprice.ui.splash.SplashViewModel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
Author: Charan Kumar
Date: 2019-07-15
 */
class FuelPriceViewModelFactory constructor(
    private val prefRepo: PrefRepo,
    private val fuelPriceRepo: FuelPriceRepo
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            Log.d("FuelPriceViewModelFact", "1. $prefRepo")
            return SplashViewModel(prefRepo) as T

        } else if (modelClass.isAssignableFrom(ChooseStateViewModel::class.java)) {
            Log.d("FuelPriceViewModelFact", "1. $prefRepo")
            return ChooseStateViewModel(prefRepo) as T

        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            Log.d("FuelPriceViewModelFact", "1. $prefRepo")
            return HomeViewModel(prefRepo) as T

        } else if (modelClass.isAssignableFrom(BrandViewModel::class.java)) {
            Log.d("FuelPriceViewModelFact", "1. $prefRepo")
            return BrandViewModel(fuelPriceRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}