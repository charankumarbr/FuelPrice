package `in`.charan.fuelprice.data

import `in`.charan.fuelprice.di.app.ApplicationContext
import `in`.charan.fuelprice.model.State
import `in`.charan.fuelprice.util.AppConstants
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
Author: Charan Kumar
Date: 2019-07-15
 */
class PrefRepo @Inject constructor(@ApplicationContext context: Context) {

    //private var context = context

    private var sharedPref: SharedPreferences = context.getSharedPreferences("app_pref", Context.MODE_PRIVATE)

    fun getStateName(): String? = sharedPref.getString(AppConstants.PrefConstants.STATE_NAME, null)
    fun setState(state: State) {
        val editor = sharedPref.edit()
        editor.putString(AppConstants.PrefConstants.STATE_NAME, state.name)
        editor.putString(AppConstants.PrefConstants.STATE_HP_CODE, state.hpCode)
        editor.putString(AppConstants.PrefConstants.STATE_IOC_CODE, state.iocCode)
        editor.apply()
    }

    fun getStateHPCode(): String? = sharedPref.getString(AppConstants.PrefConstants.STATE_HP_CODE, "")

    fun getStateIOCCode(): String? = sharedPref.getString(AppConstants.PrefConstants.STATE_IOC_CODE, "")

}