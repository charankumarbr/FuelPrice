package `in`.charan.fuelprice.model

/**
Author: Charan Kumar
Date: 2019-07-18
 */
data class CustomResponseIOC(
    val district: String,
    val modifiedResponseIOC: ModifiedResponseIOC
)

data class ModifiedResponseIOC(
    val minPetrol: Float, val maxPetrol: Float,
    val minDiesel: Float, val maxDiesel: Float
)