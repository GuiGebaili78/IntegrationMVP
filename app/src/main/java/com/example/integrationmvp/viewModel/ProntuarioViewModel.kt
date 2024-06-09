package com.example.integrationmvp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationmvp.api.ProntuarioApiClient
import com.example.integrationmvp.api.MedicoApiClient
import com.example.integrationmvp.api.PacienteApiClient
import com.example.integrationmvp.model.ProntuarioModel
import com.example.integrationmvp.model.MedicoModel
import com.example.integrationmvp.model.PacienteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProntuarioViewModel : ViewModel() {
    private val _prontuarios = MutableStateFlow<List<ProntuarioModel>>(emptyList())
    val prontuarios: StateFlow<List<ProntuarioModel>> get() = _prontuarios

    private val _medicos = MutableStateFlow<List<MedicoModel>>(emptyList())
    val medicos: StateFlow<List<MedicoModel>> get() = _medicos

    private val _pacientes = MutableStateFlow<List<PacienteModel>>(emptyList())
    val pacientes: StateFlow<List<PacienteModel>> get() = _pacientes

    private var isFetchingProntuarios = false
    private var isFetchingMedicos = false
    private var isFetchingPacientes = false

    init {
        fetchProntuarios()
        fetchMedicos()
        fetchPacientes()
    }

    fun fetchProntuarios() {
        if (!isFetchingProntuarios) {
            isFetchingProntuarios = true
            viewModelScope.launch {
                try {
                    val prontuarios = ProntuarioApiClient.apiService.getProntuarios()
                    _prontuarios.value = prontuarios
                } catch (e: Exception) {
                    // Handle error
                } finally {
                    isFetchingProntuarios = false
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
                ProntuarioApiClient.apiService.createProntuario(prontuario)
                fetchProntuarios()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
