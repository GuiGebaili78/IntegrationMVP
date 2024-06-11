package com.example.integrationmvp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationmvp.api.ConsultaApiClient
import com.example.integrationmvp.api.MedicoApiClient
import com.example.integrationmvp.api.PacienteApiClient
import com.example.integrationmvp.model.ConsultaModel
import com.example.integrationmvp.model.MedicoModel
import com.example.integrationmvp.model.PacienteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ConsultaViewModel : ViewModel() {
    private val _consultas = MutableStateFlow<List<ConsultaModel>>(emptyList())
    val consultas: StateFlow<List<ConsultaModel>> get() = _consultas

    private val _medicos = MutableStateFlow<List<MedicoModel>>(emptyList())
    val medicos: StateFlow<List<MedicoModel>> get() = _medicos

    private val _pacientes = MutableStateFlow<List<PacienteModel>>(emptyList())
    val pacientes: StateFlow<List<PacienteModel>> get() = _pacientes

    private val _selectedConsulta = MutableStateFlow<ConsultaModel?>(null)
    val selectedConsulta: StateFlow<ConsultaModel?> get() = _selectedConsulta

    private var isFetching = false

    private var isFetchingConsultas = false
    private var isFetchingMedicos = false
    private var isFetchingPacientes = false

    init {
        fetchConsultas()
        fetchMedicos()
        fetchPacientes()
    }

    fun fetchConsultas() {
        if (!isFetching) {
            isFetching = true
            viewModelScope.launch {
                try {
                    val consultas = ConsultaApiClient.apiService.getConsultas()
                    _consultas.value = consultas
                    Log.d("ConsultaAPI", "sucesso: ${_consultas.value.toString()}")
                } catch (e: HttpException) {
                    val responseBody = e.response()?.errorBody()?.string()
                    Log.d("ConsultaAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                    Log.d("ConsultaAPI", "Resposta de erro: $responseBody")
                } catch (e: Exception) {
                    Log.d("ConsultaAPI", "Erro geral: ${e.toString()}")
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

    fun createConsulta(consulta: ConsultaModel) {
        viewModelScope.launch {
            try {
                val newConsulta = ConsultaApiClient.apiService.createConsulta(consulta)
                _consultas.value = _consultas.value + newConsulta
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("ConsultaAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("ConsultaAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("ConsultaAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getConsulta(id: Long) {
        viewModelScope.launch {
            try {
                val consulta = ConsultaApiClient.apiService.getConsulta(id)
                _selectedConsulta.value = consulta
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("ConsultaAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("ConsultaAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("ConsultaAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getConsultas(): List<ConsultaModel> {
        return _consultas.value
    }

    fun updateConsulta(id: Long, consulta: ConsultaModel) {
        viewModelScope.launch {
            try {
                val updatedConsulta = ConsultaApiClient.apiService.updateConsulta(id, consulta)
                _consultas.value = _consultas.value.map { if (it.consultaId == id) updatedConsulta else it }
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("ConsultaAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("ConsultaAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("ConsultaAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun deleteConsulta(id: Long) {
        viewModelScope.launch {
            try {
                ConsultaApiClient.apiService.deleteConsulta(id)
                _consultas.value = _consultas.value.filter { it.consultaId != id }
            } catch (e: Exception) {
                Log.d("ConsultaAPI", "Erro geral: ${e.toString()}")
            }
        }
    }




}
