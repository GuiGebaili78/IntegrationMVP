package com.example.integrationmvp.model

import android.os.Build
import androidx.annotation.RequiresApi

data class MedicoModel @RequiresApi(Build.VERSION_CODES.O) constructor(
    val medicoId: Long = 0,
    val nomeMedico: String? = null,
    val crm: String? = null,
    val especialidade: String? = null,
    val contato: String? = null,
)