package com.example.lab3nav.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lab3nav.data.InventoryUiState

@Composable
fun ListScreen(
    prodList : List<InventoryUiState>,
    onDoneButtonClicked : () -> Unit = {},
    modifier : Modifier = Modifier
) {
Column(Modifier.fillMaxSize()) {
      LazyColumn(Modifier.weight(1f)) {
                items(prodList) {
                    Column(){
                       Text(text = it.productName)
                      Text(text = it.productCategory)
                      Text(text = it.expirationDate)
                     Text(text = it.quantity.toString())
                }
                 Button(onClick = onDoneButtonClicked, enabled = true) {
                            Text(text = "Done")}
                    }
        }
    }
}

@Composable
fun ProductCard(modifier : Modifier = Modifier){
    Card{
        Row{
            Box{

            }
        }
    }
}