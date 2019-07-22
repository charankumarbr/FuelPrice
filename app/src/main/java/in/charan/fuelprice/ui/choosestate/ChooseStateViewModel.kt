package `in`.charan.fuelprice.ui.choosestate

import `in`.charan.fuelprice.data.PrefRepo
import `in`.charan.fuelprice.model.State
import `in`.charan.fuelprice.ui.home.HomeActivity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel

/**
Author: Charan Kumar
Date: 2019-07-16
 */
class ChooseStateViewModel(private val prefRepo: PrefRepo) : ViewModel() {

    fun setState(state: State) {
        prefRepo.setState(state)
    }

    fun getHomeIntent(context: Context) = Intent(context, HomeActivity::class.java)

}