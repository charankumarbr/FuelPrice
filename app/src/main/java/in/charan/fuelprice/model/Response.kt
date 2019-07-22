package `in`.charan.fuelprice.model

/**
Author: Charan Kumar
Date: 2019-07-17
 */
class Response<T> private constructor(val status: Int, val data: T?, val throwable: Throwable?) {

    companion object {

        const val STATUS_LOADING = 100

        const val STATUS_SUCCESS = 200

        const val STATUS_ERROR = 400

        @JvmStatic
        fun <T> success(data: T): Response<T> = Response(STATUS_SUCCESS, data, null)

        @JvmStatic
        fun <T> error(throwable: Throwable): Response<T> = Response(STATUS_ERROR, null, throwable)

        @JvmStatic
        fun <T> loading(): Response<T> = Response(STATUS_LOADING, null, null)
    }
}