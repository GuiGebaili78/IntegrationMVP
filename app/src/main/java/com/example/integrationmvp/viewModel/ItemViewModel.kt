package com.example.integrationmvp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationmvp.api.ApiClient
import com.example.integrationmvp.api.Item
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> get() = _items

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            try {
                _items.value = ApiClient.apiService.getItems()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun createItem(name: String) {
        viewModelScope.launch {
            try {
                val newItem = ApiClient.apiService.createItem(Item(id = 0, name = name))
                _items.value = _items.value + newItem
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun updateItem(id: Long, name: String) {
        viewModelScope.launch {
            try {
                val updatedItem = ApiClient.apiService.updateItem(id, Item(id = id, name = name))
                _items.value = _items.value.map { if (it.id == id) updatedItem else it }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun deleteItem(id: Long) {
        viewModelScope.launch {
            try {
                ApiClient.apiService.deleteItem(id)
                _items.value = _items.value.filter { it.id != id }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
