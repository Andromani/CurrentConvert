package com.currentconvert.openExchangeRate.home.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.currentconvert.`in`.R
import com.currentconvert.openExchangeRate.MyHelper
import com.currentconvert.openExchangeRate.data.CurrenciesDetails
import com.currentconvert.openExchangeRate.data.bowlersRankingsList
import com.currentconvert.openExchangeRate.home.compose.HomeScreen
import com.currentconvert.openExchangeRate.ui.theme.CurrentConvertTheme
import com.currentconvert.openExchangeRate.home.viewmodel.CurrenciesViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            removeDuplicateFromArrayList()
        }
    }

    private fun removeDuplicateFromArrayList() {
        val removeDuplicateList = bowlersRankingsList.toSet()
        println("distinct : $removeDuplicateList")
        MyHelper.getInstance(this)
    }
}











@Composable
private fun MyApp() {
    val currenciesViewModel: CurrenciesViewModel = viewModel()
    CurrentConvertTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column {
                HomeScreen(currenciesViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}