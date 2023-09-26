package com.currentconvert.openExchangeRate.api

import com.google.gson.JsonObject
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenExchangeRatesApi {
    @GET("currencies.json")
    suspend fun getCurrenciesList(@Query("app_id") appId: String): HashMap<String, String>
}