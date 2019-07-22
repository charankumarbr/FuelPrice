package `in`.charan.fuelprice.di.home

import `in`.charan.fuelprice.di.app.AppComponent
import `in`.charan.fuelprice.ui.home.BrandFragment
import dagger.Component

/**
Author: Charan Kumar
Date: 2019-07-17
 */
@BrandScope
@Component(dependencies = [AppComponent::class], modules = [BrandModule::class])
interface BrandComponent {

    fun inject(fragment: BrandFragment)
}