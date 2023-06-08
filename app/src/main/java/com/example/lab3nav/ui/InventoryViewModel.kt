package com.example.lab3nav.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lab3nav.InventoryApplication
import com.example.lab3nav.data.InventoryRepository
import com.example.lab3nav.data.InventoryUiState
import com.example.lab3nav.network.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface ItemUiState {
    data class Success(val products: Item) : ItemUiState
    object Error : ItemUiState
    object Loading : ItemUiState
}


class InventoryViewModel(private val inventoryRepository : InventoryRepository) : ViewModel() {
var itemUiState: ItemUiState by mutableStateOf(ItemUiState.Loading)
  private set

//    init{
//        getBarcodeProducts()
//    }

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

    fun setCategory(category : String){
        _uiState.update{state -> state.copy(productCategory = category)
        }
    }

    fun setExpiration(expirationDate : String){
        _uiState.update{state -> state.copy(expirationDate = expirationDate)
        }
    }

    fun setBarcode(barcode : String){
        _uiState.update{state -> state.copy(Barcode = barcode)}
    }

    fun resetProduct(){
        _uiState.value = InventoryUiState()
    }

    fun saveProduct(){
        productList.add(_uiState.value)
    }

    fun getBarcodeProducts(onDone : () -> Unit) {
        viewModelScope.launch {
            try {
                val result = inventoryRepository.getBarcodeProducts(_uiState.value.Barcode)
                //check result
                setProductName(result.products[0].title)
                setCategory(result.products[0].category)
                onDone()
            } catch(e : Error){
            
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as InventoryApplication)
                val inventoryRepository = application.container.inventoryRepository
                InventoryViewModel(inventoryRepository = inventoryRepository)
            }
        }
    }
}