package com.example.integrationmvp.model

import android.os.Build
import androidx.annotation.RequiresApi

data class ProntuarioModel @RequiresApi(Build.VERSION_CODES.O) constructor(
    val prontuarioId: Long = 0,
    val pacienteId: String? = null,
    val medicoId: String? = null,
    val historicoPaciente: String? = null,
    val historicoFamiliar: String? = null,
    val medicamento: String? = null,
    val triagem: String? = null,
    val exames: String? = null,
    val dataProntuario: String? = null,
)
