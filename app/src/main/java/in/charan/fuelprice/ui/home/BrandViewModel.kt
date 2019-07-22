package `in`.charan.fuelprice.ui.home

import `in`.charan.fuelprice.model.CustomResponseIOC
import `in`.charan.fuelprice.model.Response
import `in`.charan.fuelprice.model.ResponseHP
import `in`.charan.fuelprice.repo.FuelPriceRepo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BrandViewModel(private val fuelPriceRepo: FuelPriceRepo) : ViewModel() {

    private val disposable = CompositeDisposable()

    lateinit var selectedStateCode: String

    private val hpData: MutableLiveData<Response<ArrayList<ResponseHP>>> = MutableLiveData()
    fun hpDataObservable(): LiveData<Response<ArrayList<ResponseHP>>> = hpData
    fun getHPData() {
        hpData.value = Response.loading()
        fuelPriceRepo.getHPData(selectedStateCode, object : SingleObserver<ArrayList<ResponseHP>> {
            override fun onSuccess(data: ArrayList<ResponseHP>) {
                hpData.value = Response.success(data)
            }

            override fun onSubscribe(d: Disposable) {
                disposable.add(d)
            }

            override fun onError(e: Throwable) {
                hpData.value = Response.error(e)
            }
        })
    }

    private val iocData: MutableLiveData<Response<ArrayList<CustomResponseIOC>>> = MutableLiveData()
    fun iocDataObservable(): LiveData<Response<ArrayList<CustomResponseIOC>>> = iocData
    fun getIOCData() {
        iocData.value = Response.loading()
        fuelPriceRepo.getIOCData(selectedStateCode, object : SingleObserver<ArrayList<CustomResponseIOC>> {
            override fun onSuccess(data: ArrayList<CustomResponseIOC>) {
                //iocData.value = Response.success(data)
                /*val actualMap = HashMap<String, ArrayList<ResponseIOC>>()
                actualMap["ALL"] = data
                manipulateData(actualMap)*/
                Log.d("BrandViewModel", "getIOCData ${data.size}")
                iocData.value = Response.success(data)
            }

            override fun onSubscribe(d: Disposable) {
                disposable.add(d)
            }

            override fun onError(e: Throwable) {
                iocData.value = Response.error(e)
            }
        })
    }

    /*private fun manipulateData(actualMap: HashMap<String, ArrayList<ResponseIOC>>) {
        val mapObs: Observable<MutableMap.MutableEntry<String, ArrayList<ResponseIOC>>> =
            SingleToObservable.fromIterable(actualMap.entries)

        val mapped = HashMap<String, ArrayList<ResponseIOC>>()
        mapObs.subscribeOn(Schedulers.computation())
            .map { eachItemInMap ->
                val actualList = eachItemInMap.value
                actualList.forEach { responseIOC ->
                    Log.d("BrandViewModel", "manipulateData ${Thread.currentThread().name}")
                    val district = responseIOC.district!!
                    if (mapped.containsKey(district)) {
                        mapped[district]?.add(responseIOC)

                    } else {
                        val list = ArrayList<ResponseIOC>()
                        list.add(responseIOC)
                        mapped[district] = list
                    }
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                iocData.value = Response.success(mapped)
            }
            .subscribe()
    }*/

    public override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}
