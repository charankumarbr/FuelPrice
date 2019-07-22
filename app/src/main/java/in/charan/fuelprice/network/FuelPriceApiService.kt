package `in`.charan.fuelprice.network

import `in`.charan.fuelprice.model.ResponseHP
import `in`.charan.fuelprice.model.ResponseIOC
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
Author: Charan Kumar
Date: 2019-07-15
 */
interface FuelPriceApiService {

    @GET("https://daily-fuel-prices-india.p.rapidapi.com/api/BP/Getboundingbox")
    fun getBPLocationCoordinates()

    @GET("https://daily-fuel-prices-india.p.rapidapi.com/api/Bpprice")
    fun getBPPrice(
        @Query("latitude1") latitude1: String,
        @Query("longitude1") longitude1: String,
        @Query("latitude2") latitude2: String,
        @Query("longitude2") longitude2: String
    )

    @GET("https://daily-fuel-prices-india.p.rapidapi.com/api/proxy/hp/states/{statecode}")
    fun getHPPrice(@Path("statecode") stateCode: String): Single<ArrayList<ResponseHP>>

    @GET("https://daily-fuel-prices-india.p.rapidapi.com/api/ioc/states/{statecode}")
    fun getIOCPrice(@Path("statecode") stateCode: String): Single<ArrayList<ResponseIOC>>

}