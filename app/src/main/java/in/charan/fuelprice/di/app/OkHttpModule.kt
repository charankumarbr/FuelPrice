package `in`.charan.fuelprice.di.app

import `in`.charan.fuelprice.util.AppConstants
import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

/**
Author: Charan Kumar
Date: 2019-07-15
 */
@Module(includes = [ContextModule::class])
class OkHttpModule {

    private val timeout = 90L

    private val PRAGMA = "Pragma"
    private val CACHE_CONTROL = "Cache-Control"
    private val EXPIRES = "Expires"

    @Provides
    @AppScope
    fun okHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
        okHttpClientBuilder.build()

    @Provides
    @AppScope
    fun okHttpClientBuilder(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: Interceptor
    ) =
        OkHttpClient.Builder()
            .addNetworkInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .cache(cache)

    @Provides
    @AppScope
    fun cache(@OkHttpCache cacheFile: File): Cache {
        val cacheSize = 10L * 1000 * 1000
        return Cache(cacheFile, cacheSize)
    }

    @Provides
    @AppScope
    @OkHttpCache
    fun cacheFile(@ApplicationContext context: Context): File {
        val okhttpCacheDir = "okhttp_cache"
        val cacheDir = File(context.cacheDir, okhttpCacheDir)
        cacheDir.mkdirs()
        return cacheDir
    }

    @Provides
    @AppScope
    fun loggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @AppScope
    fun networkInterceptor(cacheControl: CacheControl): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .addHeader("X-RapidAPI-Key", AppConstants.UrlConstants.X_RapidAPI_Key)
                .addHeader("X-RapidAPI-Host", AppConstants.UrlConstants.X_Rapid_Host)

            val newRequest = requestBuilder.build()
            val response = chain.proceed(newRequest)
            response.networkResponse()?.let {
                Log.d("OkHttpModule", "Network Response")
            }

            response.cacheResponse()?.let {
                Log.d("OkHttpModule", "Cache Response")
            }

            response.newBuilder()
                .removeHeader(PRAGMA)
                .removeHeader(CACHE_CONTROL)
                .removeHeader(EXPIRES)
                .addHeader(CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    @Provides
    @AppScope
    fun cacheControl(): CacheControl {
        return CacheControl.Builder()
            .maxAge(12, TimeUnit.HOURS)
            .build()
    }

}