package com.at.checkyourcrypto.api

import com.at.checkyourcrypto.api.model.CryptoTicker
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable

interface CryptoApiService {

    @GET("ticker/?limit=200")
    fun getTop200Cryptos(): Observable<ArrayList<CryptoTicker>>

    /** * Companion object to create the CryptoApiService */
    companion object Factory {
        fun create(): CryptoApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.coinmarketcap.com/v1/")
                    .build()
            return retrofit.create(CryptoApiService::class.java)
        }
    }
}