package `in`.charan.fuelprice.util

import android.view.View

/**
Author: Charan Kumar
Date: 2019-07-15
 */
class ExtensionUtil {

    companion object {

        @JvmStatic
        fun View.visible() {
            visibility = View.VISIBLE
        }

        @JvmStatic
        fun View.gone() {
            visibility = View.GONE
        }

        @JvmStatic
        fun View.invisible() {
            visibility = View.INVISIBLE
        }

        @JvmStatic
        fun String.sentenceCase(): String {
            val parts = split(" ")
            val builder = StringBuilder()
            parts.forEach {
                if (builder.isNotEmpty()) {
                    builder.append(" ")
                }
                builder.append(it[0].toUpperCase())
                builder.append(it.substring(1).toLowerCase())
            }
            return builder.toString()
        }
    }

}