package `in`.charan.fuelprice.di.app

import `in`.charan.fuelprice.data.PrefRepo
import android.content.Context
import dagger.Module
import dagger.Provides

/**
Author: Charan Kumar
Date: 2019-07-15
 */
@Module(includes = [ContextModule::class])
class PrefModule {

    @Provides
    @AppScope
    fun prefRepo(@ApplicationContext context: Context) = PrefRepo(context)

    /*@Provides
    @AppScope
    fun state(prefRepo: PrefRepo): State {
        return State(prefRepo.getStateName()!!, prefRepo.getStateHPCode()!!, prefRepo.getStateIOCCode()!!)
    }*/
}