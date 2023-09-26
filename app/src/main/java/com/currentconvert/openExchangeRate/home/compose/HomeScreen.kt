package com.currentconvert.openExchangeRate.home.compose

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.currentconvert.`in`.R
import com.currentconvert.openExchangeRate.data.CurrenciesDetails
import com.currentconvert.openExchangeRate.home.viewmodel.CurrenciesViewModel
import com.currentconvert.openExchangeRate.ui.theme.mooliFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(currenciesViewModel: CurrenciesViewModel) {
    TopAppBar(title = {
        MyTextView(stringResource(R.string.app_name), 24.sp)
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ShowOutlinedTextField()
        ShowSpinnerView(currenciesViewModel.currenciesMutableStateList.value)
    }
}

@Composable
private fun ShowOutlinedTextField() {
    var currentAmount by rememberSaveable {
        mutableStateOf("")
    }
    val preText = stringResource(id = R.string.enter_the_amount)
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        value = currentAmount,
        onValueChange = { newText ->
            currentAmount = newText
        },
        label = {
            MyTextView(preText, 14.sp)
        },
        placeholder = {
            MyTextView(preText, 14.sp, textColor = Color.LightGray)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSpinnerView(currenciesList: List<CurrenciesDetails>) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedOptionText by rememberSaveable { mutableStateOf("") }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = {
                MyTextView(stringResource(id = R.string.select_currency), 14.sp)
            },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            currenciesList.forEach { selectionOption ->
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    text = {
                        MyTextView(selectionOption.currencyName, 14.sp)
                    },
                    onClick = {
                        selectedOptionText = selectionOption.currencyName
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Composable
private fun MyTextView(myText: String, fontSize: TextUnit, textColor: Color = Color.Blue) {
    Text(
        myText, style = TextStyle(
            color = textColor,
            fontSize = fontSize,
            fontFamily = mooliFont
        )
    )
}