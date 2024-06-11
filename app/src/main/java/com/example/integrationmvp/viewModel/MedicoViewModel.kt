package com.example.integrationmvp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationmvp.api.MedicoApiClient
import com.example.integrationmvp.api.PacienteApiClient
import com.example.integrationmvp.model.MedicoModel
import com.example.integrationmvp.model.PacienteModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import kotlinx.coroutines.CompletableDeferred
class MedicoViewModel : ViewModel() {
    private val _medicos = MutableStateFlow<List<MedicoModel>>(emptyList())
    val medicos: StateFlow<List<MedicoModel>> get() = _medicos

    private val _selectedMedico = MutableStateFlow<MedicoModel?>(null)
    val selectedMedico: StateFlow<MedicoModel?> get() = _selectedMedico

    private var isFetching = false

    fun fetchMedicos() {
        if (!isFetching) {
            isFetching = true
            viewModelScope.launch {
                try {
                    val medicos = MedicoApiClient.apiService.getMedicos()
                    _medicos.value = medicos
                    Log.d("MedicoAPI", "sucesso: ${_medicos.value.toString()}")
                } catch (e: HttpException) {
                    val responseBody = e.response()?.errorBody()?.string()
                    Log.d("MedicoAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                    Log.d("MedicoAPI", "Resposta de erro: $responseBody")
                } catch (e: Exception) {
                    Log.d("MedicoAPI", "Erro geral: ${e.toString()}")
                } finally {
                    isFetching = false
                }
            }
        }
    }

    fun createMedico(medico: MedicoModel) {
        viewModelScope.launch {
            try {
                val newMedico = MedicoApiClient.apiService.createMedico(medico)
                _medicos.value = _medicos.value + newMedico
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("MedicoAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("MedicoAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("MedicoAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getMedico(id: Long) {
        viewModelScope.launch {
            try {
                val medico = MedicoApiClient.apiService.getMedico(id)
                _selectedMedico.value = medico
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("MedicoAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("MedicoAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                Log.d("MedicoAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getMedicos(): List<MedicoModel> {
        return _medicos.value
    }

    fun updateMedico(id: Long, medico: MedicoModel) {
        viewModelScope.launch {
            try {
                val updatedMedico = MedicoApiClient.apiService.updateMedico(id, medico)
                _medicos.value = _medicos.value.map { if (it.medicoId == id) updatedMedico else it }
            } catch (e: Exception) {
                Log.d("MedicoAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun deleteMedico(id: Long) {
        viewModelScope.launch {
            try {
                MedicoApiClient.apiService.deleteMedico(id)
                _medicos.value = _medicos.value.filter { it.medicoId != id }
            } catch (e: Exception) {
                Log.d("MedicoAPI", "Erro geral: ${e.toString()}")
            }
        }
    }
}
