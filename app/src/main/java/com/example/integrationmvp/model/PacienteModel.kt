package com.example.integrationmvp.model

import android.os.Build
import androidx.annotation.RequiresApi


data class PacienteModel @RequiresApi(Build.VERSION_CODES.O) constructor(
    val pacienteId: Long = 0,
    val nomePaciente: String? = null,
    val cpf: String? = null,
    val dataNascimento: String? = null,
    val genero: String? = null,
    val endereco: String? = null,
    val contato: String? = null
)