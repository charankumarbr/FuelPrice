package `in`.charan.fuelprice.ui.home

import `in`.charan.fuelprice.FuelPriceApplication
import `in`.charan.fuelprice.R
import `in`.charan.fuelprice.di.home.DaggerHomeComponent
import `in`.charan.fuelprice.di.home.HomeModule
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: BrandFragmentAdapter

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
    }

    private fun init() {
        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.menu_home)

        ahTlBrands.setupWithViewPager(ahVpBrands)

        val appComponent = (application as FuelPriceApplication).getAppComponent()
        DaggerHomeComponent.builder()
            .appComponent(appComponent)
            .homeModule(HomeModule(this, supportFragmentManager))
            .build().inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        adapter.brands = viewModel.getBrandsForState()
        adapter.selectedState = viewModel.state
        ahVpBrands.adapter = adapter

        toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_change_state -> {
                    startActivity(viewModel.getChooseStateIntent(this@HomeActivity))
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}
