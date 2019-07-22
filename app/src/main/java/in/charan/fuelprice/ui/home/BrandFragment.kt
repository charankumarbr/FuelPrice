package `in`.charan.fuelprice.ui.home

import `in`.charan.fuelprice.FuelPriceApplication
import `in`.charan.fuelprice.R
import `in`.charan.fuelprice.di.home.BrandModule
import `in`.charan.fuelprice.di.home.DaggerBrandComponent
import `in`.charan.fuelprice.model.Brand
import `in`.charan.fuelprice.model.CustomResponseIOC
import `in`.charan.fuelprice.model.Response
import `in`.charan.fuelprice.model.ResponseHP
import `in`.charan.fuelprice.util.AppConstants
import `in`.charan.fuelprice.util.ExtensionUtil.Companion.gone
import `in`.charan.fuelprice.util.ExtensionUtil.Companion.visible
import `in`.charan.fuelprice.util.Util
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_brand.*
import java.util.*
import javax.inject.Inject

class BrandFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var hpAdapter: HPAdapter

    @Inject
    lateinit var iocAdapter: IOCAdapter

    private lateinit var viewModel: BrandViewModel

    private lateinit var brandName: String
    private lateinit var stateCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            brandName = it.getString(AppConstants.BundleConstants.BRAND, "")
            stateCode = it.getString(AppConstants.BundleConstants.STATE_CODE, "")
        }
        val appComponent = (activity?.application as FuelPriceApplication).getAppComponent()
        DaggerBrandComponent.builder()
            .appComponent(appComponent)
            .brandModule(BrandModule(context!!))
            .build()
            .inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BrandViewModel::class.java)
        viewModel.selectedStateCode = stateCode
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_brand, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (brandName == Brand.BRAND_HP) {
            viewModel.getHPData()

        } else if (brandName == Brand.BRAND_IOC) {
            viewModel.getIOCData()
        }
    }

    private fun initObservers() {
        if (brandName == Brand.BRAND_HP) {
            initHPObservers()

        } else if (brandName == Brand.BRAND_IOC) {
            initIOCObservers()
        }
    }

    private fun initHPObservers() {
        viewModel.hpDataObservable().observe(this, Observer { response ->
            when (response.status) {
                Response.STATUS_SUCCESS -> {
                    setHPData(response.data!!)
                }

                Response.STATUS_ERROR -> {
                    bfPbLoading.gone()
                    bfTvBrand.visible()
                    val error = getString(R.string.error)
                    bfTvBrand.text = Util.parseError(response.throwable)
                }

                Response.STATUS_LOADING -> {
                    bfPbLoading.visible()
                }
            }
        })
    }

    private fun setHPData(data: ArrayList<ResponseHP>) {
        bfRvData.layoutManager = linearLayoutManager
        hpAdapter.hpData = data
        bfRvData.adapter = hpAdapter
        bfTvBrand.gone()
        bfPbLoading.gone()
        bfRvData.visible()
    }

    private fun initIOCObservers() {
        viewModel.iocDataObservable().observe(this, Observer { response ->
            when (response.status) {
                Response.STATUS_SUCCESS -> {
                    setIOCData(response.data!!)
                }

                Response.STATUS_ERROR -> {
                    bfPbLoading.gone()
                    bfTvBrand.visible()
                    bfTvBrand.text = Util.parseError(response.throwable)
                }

                Response.STATUS_LOADING -> {
                    bfPbLoading.visible()
                }
            }
        })
        viewModel.getIOCData()
    }

    private fun setIOCData(data: ArrayList<CustomResponseIOC>) {
        bfRvData.layoutManager = linearLayoutManager
        iocAdapter.iocData = data
        bfRvData.adapter = iocAdapter
        bfTvBrand.gone()
        bfPbLoading.gone()
        bfRvData.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onCleared()
    }

    companion object {
        fun newInstance(brandName: String, stateCode: String): BrandFragment {
            return BrandFragment().apply {
                val bundle = Bundle()
                bundle.putString(AppConstants.BundleConstants.BRAND, brandName)
                bundle.putString(AppConstants.BundleConstants.STATE_CODE, stateCode)
                arguments = bundle
            }
        }
    }

}
