package `in`.charan.fuelprice.di.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
Author: Charan Kumar
Date: 2019-07-15
 */
@Module(includes = [OkHttpModule::class])
class RetrofitModule {

    @Provides
    @AppScope
    fun retrofitBuilder(okHttpClient: OkHttpClient, gson: Gson) =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)

    @Provides
    @AppScope
    fun gson(): Gson = GsonBuilder().create()

}