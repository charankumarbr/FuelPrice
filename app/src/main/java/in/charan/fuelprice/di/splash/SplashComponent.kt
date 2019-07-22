package `in`.charan.fuelprice.di.splash

import `in`.charan.fuelprice.di.app.AppComponent
import `in`.charan.fuelprice.ui.splash.SplashActivity
import dagger.Component

/**
Author: Charan Kumar
Date: 2019-07-15
 */
@SplashScope
@Component(dependencies = [AppComponent::class])
interface SplashComponent {

    fun inject(activity: SplashActivity)
}