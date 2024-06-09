package com.example.integrationmvp.viewModel

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

class ConsultaViewModel : ViewModel() {
    private val _consultas = MutableStateFlow<List<ConsultaModel>>(emptyList())
    val consultas: StateFlow<List<ConsultaModel>> get() = _consultas

    private val _medicos = MutableStateFlow<List<MedicoModel>>(emptyList())
    val medicos: StateFlow<List<MedicoModel>> get() = _medicos

    private val _pacientes = MutableStateFlow<List<PacienteModel>>(emptyList())
    val pacientes: StateFlow<List<PacienteModel>> get() = _pacientes

    private var isFetchingConsultas = false
    private var isFetchingMedicos = false
    private var isFetchingPacientes = false

    init {
        fetchConsultas()
        fetchMedicos()
        fetchPacientes()
    }

    fun fetchConsultas() {
        if (!isFetchingConsultas) {
            isFetchingConsultas = true
            viewModelScope.launch {
                try {
                    val consultas = ConsultaApiClient.apiService.getConsultas()
                    _consultas.value = consultas
                } catch (e: Exception) {
                    // Handle error
                } finally {
                    isFetchingConsultas = false
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
                ConsultaApiClient.apiService.createConsulta(consulta)
                fetchConsultas()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
