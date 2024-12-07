package com.example.cryptoinfo.network

import com.example.cryptoinfo.model.Crypto
import retrofit2.http.GET

interface CryptoApi {
    @GET("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd\n")
    suspend fun getCryptos(): List<Crypto>
}