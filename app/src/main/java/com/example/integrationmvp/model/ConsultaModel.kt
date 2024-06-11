package com.example.integrationmvp.model

import android.os.Build
import androidx.annotation.RequiresApi

data class ConsultaModel @RequiresApi(Build.VERSION_CODES.O) constructor(
    val consultaId: Long = 0,
    val pacienteId: String? = null,
    val medicoId: String? = null,
    val dataConsulta: String? = null,
    val horaConsulta: String? = null,
    val localConsulta: String? = null,
    val mensagem: String? = null,
)
