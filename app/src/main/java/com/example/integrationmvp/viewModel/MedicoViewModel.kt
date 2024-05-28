package com.example.integrationmvp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationmvp.api.MedicoApiClient
import com.example.integrationmvp.model.MedicoModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException

class MedicoViewModel : ViewModel() {
    private val _medicos = MutableStateFlow<List<MedicoModel>>(emptyList())
    val medicos: StateFlow<List<MedicoModel>> get() = _medicos

    init {
        fetchMedicos()
    }

    private fun fetchMedicos() {
        viewModelScope.launch {
            try {
                _medicos.value = MedicoApiClient.apiService.getMedicos()
            } catch (e: Exception) {

            }
        }
    }

    fun createMedico(medico: MedicoModel) {
        viewModelScope.launch {
            try {
                val newMedico = MedicoApiClient.apiService.createMedico(medico)
                _medicos.value = _medicos.value + newMedico
            } catch (e: HttpException) {
                // Captura e trata erros de HTTP
                val responseBody = e.response()?.errorBody()?.string()
                Log.d("MédicoAPI", "Erro HTTP: ${e.code()} ${e.message()}")
                Log.d("MédicoAPI", "Resposta de erro: $responseBody")
            } catch (e: Exception) {
                // Captura e trata outros erros
                Log.d("MédicoAPI", "Erro geral: ${e.toString()}")
            }
        }
    }

    fun getMedico(id: Long): MedicoModel? {
        return _medicos.value?.find { it.medicoId == id }
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

            }
        }
    }

    fun deleteMedico(id: Long) {
        viewModelScope.launch {
            try {
                MedicoApiClient.apiService.deleteMedico(id)
                _medicos.value = _medicos.value.filter { it.medicoId != id }
            } catch (e: Exception) {

            }
        }
    }
}
