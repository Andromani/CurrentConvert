package com.currentconvert.openExchangeRate.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenExchangeRatesWebServices {
    private var api: OpenExchangeRatesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openexchangerates.org/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(OpenExchangeRatesApi::class.java)
    }

    suspend fun getCurrenciesList(): HashMap<String, String> {
        return api.getCurrenciesList("769cd504f7894740b34278f1931029a6")
    }
}