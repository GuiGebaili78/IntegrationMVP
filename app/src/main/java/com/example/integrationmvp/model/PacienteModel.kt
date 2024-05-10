package com.example.integrationmvp.model


import java.time.LocalDate

data class PacienteModel(
    val pacienteId: Int = 0,
    val nomePaciente: String? = null,
    val cpf: String? = null,
    val dataNascimento: LocalDate = LocalDate.now(),
    val genero: String? = null,
    val endereco: String? = null,
    val contato: String? = null
)
