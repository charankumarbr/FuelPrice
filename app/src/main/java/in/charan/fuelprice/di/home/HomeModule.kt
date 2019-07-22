package `in`.charan.fuelprice.di.home

import `in`.charan.fuelprice.di.choosestate.HomeContext
import `in`.charan.fuelprice.ui.home.BrandFragmentAdapter
import android.content.Context
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

/**
Author: Charan Kumar
Date: 2019-07-16
 */
@Module
class HomeModule constructor(
    private val context: Context,
    private val fragmentManager: FragmentManager
) {

    @Provides
    @HomeScope
    @HomeContext
    fun context(): Context = context

    @Provides
    @HomeScope
    fun fragmentAdapter(): BrandFragmentAdapter {
        return BrandFragmentAdapter(fragmentManager)
    }
}