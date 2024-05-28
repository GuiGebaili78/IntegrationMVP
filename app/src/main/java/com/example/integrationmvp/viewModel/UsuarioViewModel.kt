package com.example.integrationmvp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationmvp.api.UsuarioApiClient
import com.example.integrationmvp.model.UsuarioModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException

class UsuarioViewModel : ViewModel() {
    private val _usuarios = MutableStateFlow<List<UsuarioModel>>(emptyList())
    val usuarios: StateFlow<List<UsuarioModel>> get() = _usuarios

    init {
        fetchUsuarios()
    }

    private fun fetchUsuarios() {
        viewModelScope.launch {
            try {
                _usuarios.value = UsuarioApiClient.apiService.getUsuarios()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun createUsuario(usuario: UsuarioModel) {
        viewModelScope.launch {
            try {
                val newUsuario = UsuarioApiClient.apiService.createUsuario(usuario)
                _usuarios.value = _usuarios.value + newUsuario
            } catch (e: HttpException) {
                // Captura e trata erros de HTTP
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("UsuarioAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("UsuarioAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                // Captura e trata outros erros
                Log.d("UsuarioAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getUsuario(id: Long): UsuarioModel? {
        return _usuarios.value?.find { it.usuarioId == id }
    }

    fun getUsuarios(): List<UsuarioModel> {
        return _usuarios.value
    }

    fun updateUsuario(id: Long, usuario: UsuarioModel) {
        viewModelScope.launch {
            try {
                val updatedUsuario = UsuarioApiClient.apiService.updateUsuario(id, usuario)
                _usuarios.value = _usuarios.value.map { if (it.usuarioId == id) updatedUsuario else it }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun deleteUsuario(id: Long) {
        viewModelScope.launch {
            try {
                UsuarioApiClient.apiService.deleteUsuario(id)
                _usuarios.value = _usuarios.value.filter { it.usuarioId != id }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
