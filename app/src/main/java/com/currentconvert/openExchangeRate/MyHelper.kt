package com.currentconvert.openExchangeRate

import android.content.Context

class MyHelper private constructor(context: Context) {

    companion object : SingletonHolder<MyHelper, Context>(::MyHelper)
}
