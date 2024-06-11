package com.example.integrationmvp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationmvp.api.ConsultaApiClient
import com.example.integrationmvp.api.ProntuarioApiClient
import com.example.integrationmvp.api.MedicoApiClient
import com.example.integrationmvp.api.PacienteApiClient
import com.example.integrationmvp.model.ConsultaModel
import com.example.integrationmvp.model.ProntuarioModel
import com.example.integrationmvp.model.MedicoModel
import com.example.integrationmvp.model.PacienteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProntuarioViewModel : ViewModel() {
    private val _prontuarios = MutableStateFlow<List<ProntuarioModel>>(emptyList())
    val prontuarios: StateFlow<List<ProntuarioModel>> get() = _prontuarios

    private val _medicos = MutableStateFlow<List<MedicoModel>>(emptyList())
    val medicos: StateFlow<List<MedicoModel>> get() = _medicos

    private val _pacientes = MutableStateFlow<List<PacienteModel>>(emptyList())
    val pacientes: StateFlow<List<PacienteModel>> get() = _pacientes

    private val _selectedProntuario = MutableStateFlow<ProntuarioModel?>(null)
    val selectedProntuario: StateFlow<ProntuarioModel?> get() = _selectedProntuario

    private var isFetching = false

    private var isFetchingProntuarios = false
    private var isFetchingMedicos = false
    private var isFetchingPacientes = false

    init {
        fetchProntuarios()
        fetchMedicos()
        fetchPacientes()
    }

    fun fetchProntuarios() {
        if (!isFetching) {
            isFetching = true
            viewModelScope.launch {
                try {
                    val prontuarios = ProntuarioApiClient.apiService.getProntuarios()
                    _prontuarios.value = prontuarios
                    Log.d("ProntuarioAPI", "sucesso: ${_prontuarios.value.toString()}")
                } catch (e: HttpException) {
                    val responseBody = e.response()?.errorBody()?.string()
                    Log.d("ProntuarioAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                    Log.d("ProntuarioAPI", "Resposta de erro: $responseBody")
                } catch (e: Exception) {
                    Log.d("ProntuarioAPI", "Erro geral: ${e.toString()}")
                } finally {
                    isFetching = false
                }
            }
        }
    }

    private fun fetchMedicos() {
        if (!isFetchingMedicos) {
            isFetchingMedicos = true
            viewModelScope.launch {
                try {
                    val medicos = MedicoApiClient.apiService.getMedicos()
                    _medicos.value = medicos
                } catch (e: Exception) {
                    // Handle error
                } finally {
                    isFetchingMedicos = false
                }
            }
        }
    }

    private fun fetchPacientes() {
        if (!isFetchingPacientes) {
            isFetchingPacientes = true
            viewModelScope.launch {
                try {
                    val pacientes = PacienteApiClient.apiService.getPacientes()
                    _pacientes.value = pacientes
                } catch (e: Exception) {
                    // Handle error
                } finally {
                    isFetchingPacientes = false
                }
            }
        }
    }

    fun createProntuario(prontuario: ProntuarioModel) {
        viewModelScope.launch {
            try {
                val newProntuario = ProntuarioApiClient.apiService.createProntuario(prontuario)
                _prontuarios.value = _prontuarios.value + newProntuario
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("ProntuarioAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("ProntuarioAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("ProntuarioAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getProntuario(id: Long) {
        viewModelScope.launch {
            try {
                val prontuario = ProntuarioApiClient.apiService.getProntuario(id)
                _selectedProntuario.value = prontuario
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("ProntuarioAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("ProntuarioAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("ProntuarioAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getProntuario(): List<ProntuarioModel> {
        return _prontuarios.value
    }

    fun updateProntuario(id: Long, consulta: ProntuarioModel) {
        viewModelScope.launch {
            try {
                val updatedProntuario = ProntuarioApiClient.apiService.updateProntuario(id, consulta)
                _prontuarios.value = _prontuarios.value.map { if (it.prontuarioId == id) updatedProntuario else it }
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("ProntuarioAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("ProntuarioAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("ProntuarioAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun deleteProntuario(id: Long) {
        viewModelScope.launch {
            try {
                ProntuarioApiClient.apiService.deleteProntuario(id)
                _prontuarios.value = _prontuarios.value.filter { it.prontuarioId != id }
            } catch (e: Exception) {
                Log.d("ProntuarioAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

}
