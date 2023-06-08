@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lab3nav.ui

/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lab3nav.R
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import java.util.Calendar


@Composable
fun FormScreen(model : InventoryViewModel,
               scanner : GmsBarcodeScanner,
               onNextButtonClicked: () -> Unit = {},
               onCancelButtonClicked: () -> Unit = {},
               onScannerExit: () -> Unit = {},
               modifier: Modifier = Modifier
) { var productName by remember { mutableStateOf(model.uiState.value.productName) }
    var productCategory by remember { mutableStateOf(model.uiState.value.productCategory)}
    var expirationDate by remember { mutableStateOf(model.uiState.value.expirationDate) }
    var quantity by remember { mutableStateOf(model.uiState.value.quantity.toString()) }

    Column(
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.FillForm),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            label = R.string.Product_Name,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = productName,
            onValueChanged = { productName = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )
        EditNumberField(
            label = R.string.Product_Category,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = productCategory,
            onValueChanged = { productCategory = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )
        DatePickerField(
            label = R.string.Product_Expiration,
            viewModel = model,
            onValueChanged = {},
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        EditNumberField(
                    label = R.string.Product_Quantity,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    value = quantity,
                    onValueChanged = { quantity = it },
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .fillMaxWidth(),
        )
        Button(
            onClick =
                {
                  initiateScanning(scanner, onScannerExit, model)
                },
            enabled = true
        ){
            Text(stringResource(R.string.Scan))
        }

        Button(
            onClick =
            {
                model.setProductName(productName)
                model.setCategory(productCategory)
                model.setQuantity(quantity.toInt())
                onNextButtonClicked()
            },
            enabled = true
        ){
            Text(stringResource(R.string.Submit))
        }
        Button(onClick = onCancelButtonClicked, enabled = true) {
            Text(stringResource(R.string.Cancel))
        }

        //Spacer(modifier = Modifier.height(150.dp))
    }
}


@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun DatePickerField(
    @StringRes label: Int,
    onValueChanged: (String) -> Unit,
    viewModel: InventoryViewModel,
    modifier: Modifier = Modifier
){

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var selectedDateText by remember { mutableStateOf("") }
// Fetching current year, month and day
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDateText = "${selectedMonth + 1}/$selectedDayOfMonth/$selectedYear"
            viewModel.setExpiration(selectedDateText)
        }, year, month, dayOfMonth
    )
    TextField(
        value = selectedDateText,
        singleLine = true,
        modifier = modifier.clickable { datePicker.show() },
        onValueChange = onValueChanged,
        label = { Text(stringResource(label))},
        enabled = false
    )
}

fun initiateScanning(
    scanner : GmsBarcodeScanner,
    onExit : () -> Unit,
    model : InventoryViewModel
){
   //only for debugging purposes DELETE
    model.setBarcode("077341125112")
    model.getBarcodeProducts(onExit)
        onExit()

//        scanner.startScan().addOnSuccessListener{
//            barcode -> //Set the scanned barcode value here so we can look it up after this
//            model.setBarcode(barcode.displayValue.toString())
//            model.getBarcodeProducts(onExit)
//            onExit
//        }
//        .addOnCanceledListener{
//            onExit()
//        }
//        .addOnFailureListener{e ->  onExit() }

}
