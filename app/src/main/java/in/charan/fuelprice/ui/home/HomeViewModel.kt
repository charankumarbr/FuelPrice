package `in`.charan.fuelprice.ui.home

import `in`.charan.fuelprice.data.PrefRepo
import `in`.charan.fuelprice.model.Brand
import `in`.charan.fuelprice.model.State
import `in`.charan.fuelprice.ui.choosestate.ChooseStateActivity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel

/**
Author: Charan Kumar
Date: 2019-07-17
 */
class HomeViewModel(prefRepo: PrefRepo) : ViewModel() {

    val state: State = State(
        prefRepo.getStateName()!!, prefRepo.getStateHPCode()!!,
        prefRepo.getStateIOCCode()!!
    )

    fun getBrandsForState(): ArrayList<Brand> {
        val brands = ArrayList<Brand>()
        if (state.hpCode.length > 1) {
            brands.add(Brand(Brand.BRAND_HP))
        }
        if (state.iocCode.length > 1) {
            brands.add(Brand(Brand.BRAND_IOC))
        }
        return brands
    }

    fun getChooseStateIntent(context: Context) = Intent(context, ChooseStateActivity::class.java)
}