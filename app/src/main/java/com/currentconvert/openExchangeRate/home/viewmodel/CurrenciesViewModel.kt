package com.currentconvert.openExchangeRate.home.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currentconvert.openExchangeRate.data.CurrenciesDetails
import com.currentconvert.openExchangeRate.repository.CurrenciesRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrenciesViewModel(
    private val repository: CurrenciesRepository = CurrenciesRepository()
) : ViewModel() {

    val currenciesMutableStateList: MutableState<List<CurrenciesDetails>> =
        mutableStateOf(emptyList())

    var currenciesList = mutableListOf<CurrenciesDetails>()

    var counterState = mutableStateOf(0)

    fun incrementCounter() {
        counterState.value++
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val currencies =
                getCurrenciesLists().entries.sortedBy { it.key }.associate { it.toPair() }
            processCurrencies(currencies)
        }
    }

    private fun processCurrencies(currencies: Map<String, String>) {
        currencies.forEach { (key, value) ->
            currenciesList.add(CurrenciesDetails(currencyCode = key, currencyName = value))
        }
        currenciesMutableStateList.value = currenciesList
    }

    private suspend fun getCurrenciesLists(): HashMap<String, String> {
        return repository.getCurrenciesList()
    }
}