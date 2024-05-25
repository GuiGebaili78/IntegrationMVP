package com.example.integrationmvp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationmvp.api.PacienteApiClient
import com.example.integrationmvp.model.PacienteModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException

class PacienteViewModel : ViewModel() {
    private val _pacientes = MutableStateFlow<List<PacienteModel>>(emptyList())
    val pacientes: StateFlow<List<PacienteModel>> get() = _pacientes

    init {
        fetchPacientes()
    }

    private fun fetchPacientes() {
        viewModelScope.launch {
            try {
                _pacientes.value = PacienteApiClient.apiService.getPacientes()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun createPaciente(paciente: PacienteModel) {
        viewModelScope.launch {
            try {
                val newPaciente = PacienteApiClient.apiService.createPaciente(paciente)
                _pacientes.value = _pacientes.value + newPaciente
            } catch (e: HttpException) {
                // Captura e trata erros de HTTP
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("PacienteAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("PacienteAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                // Captura e trata outros erros
                Log.d("PacienteAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getPaciente(id: Long): PacienteModel? {
        return _pacientes.value?.find { it.pacientId == id }
    }

    fun getPacientes(): List<PacienteModel> {
        return _pacientes.value
    }

    fun updatePaciente(id: Long, paciente: PacienteModel) {
        viewModelScope.launch {
            try {
                val updatedPaciente = PacienteApiClient.apiService.updatePaciente(id, paciente)
                _pacientes.value = _pacientes.value.map { if (it.pacientId == id) updatedPaciente else it }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun deletePaciente(id: Long) {
        viewModelScope.launch {
            try {
                PacienteApiClient.apiService.deletePaciente(id)
                _pacientes.value = _pacientes.value.filter { it.pacientId != id }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
