package com.example.integrationmvp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationmvp.api.PacienteApiClient
import com.example.integrationmvp.api.UsuarioApiClient
import com.example.integrationmvp.model.PacienteModel
import com.example.integrationmvp.model.UsuarioModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import kotlinx.coroutines.CompletableDeferred
class UsuarioViewModel : ViewModel() {
    private val _usuarios = MutableStateFlow<List<UsuarioModel>>(emptyList())
    val usuarios: StateFlow<List<UsuarioModel>> get() = _usuarios

    private val _selectedUsuario = MutableStateFlow<UsuarioModel?>(null)
    val selectedUsuario: StateFlow<UsuarioModel?> get() = _selectedUsuario


    private var isFetching = false

    fun fetchUsuarios() {
        if (!isFetching) {
            isFetching = true
            viewModelScope.launch {
                try {
                    val usuarios = UsuarioApiClient.apiService.getUsuarios()
                    _usuarios.value = usuarios
                    Log.d("UsuarioAPI", "sucesso: ${_usuarios.value.toString()}")
                } catch (e: HttpException) {
                    val responseBody = e.response()?.errorBody()?.string()
                    Log.d("UsuarioAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                    Log.d("UsuarioAPI", "Resposta de erro: $responseBody")
                } catch (e: Exception) {
                    Log.d("UsuarioAPI", "Erro geral: ${e.toString()}")
                } finally {
                    isFetching = false
                }
            }
        }
    }

    fun createUsuario(usuario: UsuarioModel) {
        viewModelScope.launch {
            try {
                val newUsuario = UsuarioApiClient.apiService.createUsuario(usuario)
                _usuarios.value = _usuarios.value + newUsuario
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("UsuarioAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("UsuarioAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("UsuarioAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getUsuario(id: Long) {
        viewModelScope.launch {
            try {
                val usuario = UsuarioApiClient.apiService.getUsuario(id)
                _selectedUsuario.value = usuario
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("UsuarioAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("UsuarioAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("UsuarioAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getUsuarios(): List<UsuarioModel> {
        return _usuarios.value
    }

    fun updateUsuario(id: Long, paciente: UsuarioModel) {
        viewModelScope.launch {
            try {
                val updatedUsuario = UsuarioApiClient.apiService.updateUsuario(id, paciente)
                _usuarios.value = _usuarios.value.map { if (it.usuarioId == id) updatedUsuario else it }
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("UsuarioAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("UsuarioAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("UsuarioAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun deleteUsuario(id: Long) {
        viewModelScope.launch {
            try {
                UsuarioApiClient.apiService.deleteUsuario(id)
                _usuarios.value = _usuarios.value.filter { it.usuarioId != id }
            } catch (e: Exception) {
                Log.d("UsuarioAPI", "Erro geral: ${e.toString()}")
            }
        }
    }
}
