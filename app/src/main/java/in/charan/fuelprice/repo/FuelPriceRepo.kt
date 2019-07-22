package `in`.charan.fuelprice.repo

import `in`.charan.fuelprice.model.CustomResponseIOC
import `in`.charan.fuelprice.model.ModifiedResponseIOC
import `in`.charan.fuelprice.model.ResponseHP
import `in`.charan.fuelprice.model.ResponseIOC
import `in`.charan.fuelprice.network.FuelPriceApiService
import `in`.charan.fuelprice.util.ExtensionUtil.Companion.sentenceCase
import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
Author: Charan Kumar
Date: 2019-07-15
 */
class FuelPriceRepo @Inject constructor(private val apiService: FuelPriceApiService) {

    fun getHPData(stateCode: String, singleObserver: SingleObserver<ArrayList<ResponseHP>>) {
        apiService.getHPPrice(stateCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(singleObserver)
    }

    fun getIOCData(stateCode: String, singleObserver: SingleObserver<ArrayList<CustomResponseIOC>>) {
        apiService.getIOCPrice(stateCode)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { response ->
                Log.d("FuelPriceRepo", "getIOCData: Thread ${Thread.currentThread().name}")
                val mapped = HashMap<String, ArrayList<ResponseIOC>>()
                response.forEach { responseIOC ->
                    val district = responseIOC.district!!
                    if (mapped.containsKey(district)) {
                        mapped[district]?.add(responseIOC)

                    } else {
                        val list = ArrayList<ResponseIOC>()
                        list.add(responseIOC)
                        mapped[district] = list
                    }
                }

                val customResponseIOC = ArrayList<CustomResponseIOC>()
                val keys = mapped.keys.sorted().iterator()
                while (keys.hasNext()) {
                    val district = keys.next()
                    val pumps = mapped[district]
                    var minPetrol = -1F
                    var maxPetrol = -1F
                    var minDiesel = -1F
                    var maxDiesel = -1F
                    pumps?.let {
                        //customResponseIOC.add(CustomResponseIOC(district, true, null))
                        it.forEach { aPump ->
                            //customResponseIOC.add(CustomResponseIOC(null, false, aPump))
                            if (!aPump.petrolPrice!!.equals("not available", true)) {
                                val petrolPrice = aPump.petrolPrice.toFloat()
                                petrolPrice.let { petrolPrice ->
                                    if (minPetrol == -1F && petrolPrice != 0F) {
                                        minPetrol = petrolPrice
                                    }
                                    if (petrolPrice < minPetrol && petrolPrice != 0F) {
                                        minPetrol = petrolPrice
                                    }
                                    if (petrolPrice > maxPetrol) {
                                        maxPetrol = petrolPrice
                                    }
                                }
                            }

                            if (!aPump.dieselPrice!!.equals("not available", true)) {
                                val dieselPrice = aPump.dieselPrice.toFloat()
                                dieselPrice.let { dieselPrice ->
                                    if (minDiesel == -1F && dieselPrice != 0F) {
                                        minDiesel = dieselPrice
                                    }
                                    if (dieselPrice < minDiesel && dieselPrice != 0F) {
                                        minDiesel = dieselPrice
                                    }
                                    if (dieselPrice > maxDiesel) {
                                        maxDiesel = dieselPrice
                                    }
                                }
                            }
                        }
                        customResponseIOC.add(
                            CustomResponseIOC(
                                district.sentenceCase(),
                                ModifiedResponseIOC(minPetrol, maxPetrol, minDiesel, maxDiesel)
                            )
                        )
                    }
                }
                customResponseIOC
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(singleObserver)
    }

}