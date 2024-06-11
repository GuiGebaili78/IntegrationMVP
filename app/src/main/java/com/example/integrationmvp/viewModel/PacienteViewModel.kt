package com.example.integrationmvp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationmvp.api.PacienteApiClient
import com.example.integrationmvp.model.PacienteModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import kotlinx.coroutines.CompletableDeferred
class PacienteViewModel : ViewModel() {
    private val _pacientes = MutableStateFlow<List<PacienteModel>>(emptyList())
    val pacientes: StateFlow<List<PacienteModel>> get() = _pacientes

    private val _selectedPaciente = MutableStateFlow<PacienteModel?>(null)
    val selectedPaciente: StateFlow<PacienteModel?> get() = _selectedPaciente

    private var isFetching = false

    fun fetchPacientes() {
        if (!isFetching) {
            isFetching = true
            viewModelScope.launch {
                try {
                    val pacientes = PacienteApiClient.apiService.getPacientes()
                    _pacientes.value = pacientes
                    Log.d("PacienteAPI", "sucesso: ${_pacientes.value.toString()}")
                } catch (e: HttpException) {
                    val responseBody = e.response()?.errorBody()?.string()
                    Log.d("PacienteAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                    Log.d("PacienteAPI", "Resposta de erro: $responseBody")
                } catch (e: Exception) {
                    Log.d("PacienteAPI", "Erro geral: ${e.toString()}")
                } finally {
                    isFetching = false
                }
            }
        }
    }

    fun createPaciente(paciente: PacienteModel) {
        viewModelScope.launch {
            try {
                val newPaciente = PacienteApiClient.apiService.createPaciente(paciente)
                _pacientes.value = _pacientes.value + newPaciente
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("PacienteAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("PacienteAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("PacienteAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getPaciente(id: Long) {
        viewModelScope.launch {
            try {
                val paciente = PacienteApiClient.apiService.getPaciente(id)
                _selectedPaciente.value = paciente
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("PacienteAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("PacienteAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("PacienteAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getPacientes(): List<PacienteModel> {
        return _pacientes.value
    }

    fun updatePaciente(id: Long, paciente: PacienteModel) {
        viewModelScope.launch {
            try {
                val updatedPaciente = PacienteApiClient.apiService.updatePaciente(id, paciente)
                _pacientes.value = _pacientes.value.map { if (it.pacienteId == id) updatedPaciente else it }
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("PacienteAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("PacienteAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("PacienteAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun deletePaciente(id: Long) {
        viewModelScope.launch {
            try {
                PacienteApiClient.apiService.deletePaciente(id)
                _pacientes.value = _pacientes.value.filter { it.pacienteId != id }
            } catch (e: Exception) {
                Log.d("PacienteAPI", "Erro geral: ${e.toString()}")
            }
        }
    }
}
