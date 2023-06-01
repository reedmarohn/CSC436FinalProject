package com.example.lab3nav.ui

import androidx.lifecycle.ViewModel
import com.example.lab3nav.data.InventoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InventoryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryUiState())
    val uiState: StateFlow<InventoryUiState> = _uiState.asStateFlow()

    val productList = mutableListOf<InventoryUiState>()

    fun setQuantity(amount : Int){
        _uiState.update{state ->
            state.copy(quantity = amount)
        }
    }

    fun setProductName(name : String){
        _uiState.update{state ->
            state.copy(productName = name)
        }
    }

    fun setCategory(category : Int){
        _uiState.update{state -> state.copy(productCategory = category)
        }
    }

    fun setExpiration(expirationDate : String){
        _uiState.update{state -> state.copy(expirationDate = expirationDate)
        }
    }

    fun resetProduct(){
        _uiState.value = InventoryUiState()
    }

    fun saveProduct(){
        productList.add(_uiState.value)
    }
}