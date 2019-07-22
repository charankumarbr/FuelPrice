package `in`.charan.fuelprice.di.choosestate

import `in`.charan.fuelprice.di.app.AppComponent
import `in`.charan.fuelprice.ui.choosestate.ChooseStateActivity
import dagger.Component

/**
Author: Charan Kumar
Date: 2019-07-15
 */
@ChooseStateScope
@Component(modules = [ChooseStateModule::class], dependencies = [AppComponent::class])
interface ChooseStateComponent {

    fun inject(activity: ChooseStateActivity)

}