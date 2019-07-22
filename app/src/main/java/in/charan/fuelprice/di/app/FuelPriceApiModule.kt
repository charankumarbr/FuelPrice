package `in`.charan.fuelprice.di.app

import `in`.charan.fuelprice.network.FuelPriceApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
Author: Charan Kumar
Date: 2019-07-15
 */
@Module(includes = [RetrofitModule::class])
class FuelPriceApiModule {

    val BASE_URL = "https://fuelprice.p.rapidapi.com/"

    @Provides
    @AppScope
    fun fuelPriceRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit {
        return retrofitBuilder.baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @AppScope
    fun fuelPriceApiModule(retrofit: Retrofit): FuelPriceApiService {
        return retrofit.create(FuelPriceApiService::class.java)
    }
}