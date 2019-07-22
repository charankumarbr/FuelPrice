package `in`.charan.fuelprice.util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import java.io.IOException

/**
Author: Charan Kumar
Date: 2019-07-15
 */
class Util {

    companion object {

        @JvmStatic
        fun isConnected(context: Context): Boolean {
            val connectivityManager: ConnectivityManager? =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager?.let { connectivityManager ->
                connectivityManager.activeNetworkInfo.let {
                    return it?.isConnected ?: false
                }
            }
            return false
        }

        @JvmStatic
        fun displayToast(message: String, context: Context) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        @JvmStatic
        fun parseError(throwable: Throwable?): String {
            return throwable?.let {
                if (throwable is IOException) {
                    "Seems like you are offline. Please check your internet connection."

                } else {
                    "Taking more time to communicate with our server. Please try again later."
                }

            } ?: "Something went wrong!"
        }
    }
}