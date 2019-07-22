package `in`.charan.fuelprice.di.home

import `in`.charan.fuelprice.R
import `in`.charan.fuelprice.di.choosestate.RupeeSymbol
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides

/**
Author: Charan Kumar
Date: 2019-07-17
 */
@Module
class BrandModule(val context: Context) {

    @Provides
    @BrandScope
    fun linearLayoutManager() = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

    @Provides
    @BrandScope
    @RupeeSymbol
    fun rupeeSymbol() = context.resources.getString(R.string.Rs)

}