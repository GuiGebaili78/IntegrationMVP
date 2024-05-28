package com.example.integrationmvp.model

import android.os.Build
import androidx.annotation.RequiresApi


data class UsuarioModel @RequiresApi(Build.VERSION_CODES.O) constructor(
    val usuarioId: Long = 0,
    val nomeUsuario: String? = null,
    val senhaUsuario: String? = null,
    val emailUsuario: String? = null
)