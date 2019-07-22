package `in`.charan.fuelprice.di.app

import android.content.Context
import dagger.Module
import dagger.Provides

/**
Author: Charan Kumar
Date: 2019-07-15
 */
@Module
class ContextModule constructor(context: Context) {

    private val context = context

    @Provides
    @AppScope
    @ApplicationContext
    fun context(): Context = context.applicationContext
}