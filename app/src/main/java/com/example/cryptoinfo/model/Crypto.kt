package com.example.cryptoinfo.model

data class Crypto(
    val name: String,
    val symbol: String,
    val current_price: Double,
    val image: String,
    val last_updated: String
)