package com.example.janaushadhifinder.model

data class Medicine(
    val brandName: String,
    val genericName: String,
    val category: String,
    val brandPrice: Int,
    val genericPrice: Int,
    val inCart: Boolean = false
) {
    fun getSavingsAmount(): Int = brandPrice - genericPrice
    fun getSavingsPercentage(): Int = ((brandPrice - genericPrice) * 100) / brandPrice
}