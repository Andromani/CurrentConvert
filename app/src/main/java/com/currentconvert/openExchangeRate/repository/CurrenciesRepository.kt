package com.currentconvert.openExchangeRate.repository

import com.currentconvert.openExchangeRate.api.OpenExchangeRatesWebServices
import com.currentconvert.openExchangeRate.data.CurrenciesListResponse

class CurrenciesRepository(private val webService: OpenExchangeRatesWebServices = OpenExchangeRatesWebServices()) {

    private var cachedCurrencies: CurrenciesListResponse? = null

    suspend fun getCurrenciesList(): HashMap<String, String> {
        return webService.getCurrenciesList()
    }

    companion object {
        @Volatile
        private var instance: CurrenciesRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: CurrenciesRepository().also { instance = it }
        }
    }
}