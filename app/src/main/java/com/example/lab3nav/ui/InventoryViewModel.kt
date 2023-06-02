package com.example.lab3nav.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab3nav.data.InventoryUiState
import com.example.lab3nav.network.ItemAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ItemUiState {
    data class Success(val photos: String) : ItemUiState
    object Error : ItemUiState
    object Loading : ItemUiState
}


class InventoryViewModel : ViewModel() {
var itemUiState: ItemUiState by mutableStateOf(ItemUiState.Loading)
  private set

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

    fun getItemPhotos() {
        viewModelScope.launch {
        itemUiState = try {
               val listResult = ItemAPI.retrofitService.getPhotos()
               ItemUiState.Success(listResult)
           } catch (e: IOException) {
              ItemUiState.Error
           }
        }
    }
}