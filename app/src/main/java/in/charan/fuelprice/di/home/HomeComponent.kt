package `in`.charan.fuelprice.di.home

import `in`.charan.fuelprice.di.app.AppComponent
import `in`.charan.fuelprice.ui.home.HomeActivity
import dagger.Component

/**
Author: Charan Kumar
Date: 2019-07-16
 */
@HomeScope
@Component(dependencies = [AppComponent::class], modules = [HomeModule::class])
interface HomeComponent {

    fun inject(activity: HomeActivity)
}