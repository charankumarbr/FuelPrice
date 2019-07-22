package `in`.charan.fuelprice.ui.splash

import `in`.charan.fuelprice.data.PrefRepo
import `in`.charan.fuelprice.ui.choosestate.ChooseStateActivity
import `in`.charan.fuelprice.ui.home.HomeActivity
import `in`.charan.fuelprice.util.Util
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel

/**
Author: Charan Kumar
Date: 2019-07-15
 */
class SplashViewModel(private val prefRepo: PrefRepo) : ViewModel() {

    fun isConnected(context: Context) = Util.isConnected(context)

    fun getHomeIntent(context: Context) = Intent(context, HomeActivity::class.java)

    fun isStateSet(): Boolean {
        return prefRepo.getStateName()?.let {
            it.trim().isNotEmpty()
        } ?: false
    }

    fun getChooseStateIntent(context: Context) = Intent(context, ChooseStateActivity::class.java)
}