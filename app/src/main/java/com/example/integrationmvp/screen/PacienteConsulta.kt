package com.example.integrationmvp.screen

import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul3
import com.example.integrationmvp.ui.theme.Azul5
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacienteConsulta(navController: NavController) {
    val paciente =  PacienteModel(1, "João", "123.456.789-00", "01/01/1990", "Masculino", "av.paulista", "123456789", "joao@example.com");

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Paciente - Consultar", color = Azul5) },

                )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues), // Use o paddingValues fornecido pelo Scaffold
                color = Azul1
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Nome: ${paciente.nome}", color = Azul5)
                    Text("CPF: ${paciente.cpf}", color = Azul5)
                    Text("Data de Nascimento: ${paciente.dataNascimento}", color = Azul5)
                    Text("Gênero: ${paciente.genero}", color = Azul5)
                    Text("Endereço: ${paciente.endereco}", color = Azul5)
                    Text("Contato: ${paciente.contato}", color = Azul5)
                    Text("Email: ${paciente.email}", color = Azul5)

                    Button(
                        onClick = { /* Handle navigation back */ },
                        colors = ButtonDefaults.buttonColors( // Set button colors
                            containerColor = Azul3, // New name for background color
                            contentColor = Color.White // Cor do Texto do Botão
                        )
                    ) {
                        Text("Voltar", color = Color.White)
                    }
                }
            }
        }
    )
}

@Preview (showSystemUi = true, showBackground = true)
@Composable
fun PacienteConsultaPreview() {
    PacienteConsulta(navController = rememberNavController())
}
