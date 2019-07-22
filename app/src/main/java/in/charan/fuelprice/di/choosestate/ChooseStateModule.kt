package `in`.charan.fuelprice.di.choosestate

import `in`.charan.fuelprice.model.State
import `in`.charan.fuelprice.ui.choosestate.ChooseStateAdapter
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides

/**
Author: Charan Kumar
Date: 2019-07-16
 */
@Module
class ChooseStateModule constructor(private val context: Context) {

    @Provides
    @ChooseStateScope
    @ChooseStateContext
    fun context(): Context = context

    @Provides
    @ChooseStateScope
    fun chooseStateAdapter(
        @ChooseStateContext context: Context,
        states: ArrayList<State>
    ) = ChooseStateAdapter(context, states)

    @Provides
    @ChooseStateScope
    fun states() = State.getAllStates()

    @Provides
    @ChooseStateScope
    fun layoutManager(@ChooseStateContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}