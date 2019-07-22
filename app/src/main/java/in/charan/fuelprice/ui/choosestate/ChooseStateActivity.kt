package `in`.charan.fuelprice.ui.choosestate

import `in`.charan.fuelprice.FuelPriceApplication
import `in`.charan.fuelprice.R
import `in`.charan.fuelprice.di.choosestate.ChooseStateModule
import `in`.charan.fuelprice.di.choosestate.DaggerChooseStateComponent
import `in`.charan.fuelprice.model.State
import `in`.charan.fuelprice.util.Util
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_choose_state.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class ChooseStateActivity : AppCompatActivity(), OnStateSelectedListener {

    private lateinit var mMiDone: MenuItem

    private var mSelectedState: State? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mViewModel: ChooseStateViewModel

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var adapter: ChooseStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_state)

        init()
    }

    private fun init() {
        toolbar.title = getString(R.string.choose_state)
        toolbar.inflateMenu(R.menu.menu_choose_state)

        val appComponent = (application as FuelPriceApplication).getAppComponent()
        val chooseStateComponent = DaggerChooseStateComponent.builder()
            .appComponent(appComponent)
            .chooseStateModule(ChooseStateModule(this))
            .build()
        chooseStateComponent.inject(this)

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChooseStateViewModel::class.java)

        mMiDone = toolbar.menu.findItem(R.id.menu_done)
        toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_done -> {
                    mSelectedState?.let { state ->
                        mViewModel.setState(state)
                        val intent = mViewModel.getHomeIntent(this@ChooseStateActivity)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()

                    } ?: Util.displayToast("Please select the state", applicationContext)
                    true
                }
                else -> {
                    false
                }
            }
        }

        acsRvStates.layoutManager = linearLayoutManager
        acsRvStates.adapter = adapter
    }

    override fun onStateSelected(selectedState: State?) {
        mMiDone.isEnabled = selectedState != null
        mSelectedState = selectedState
    }
}
