package com.example.integrationmvp.screen.medico

import com.example.integrationmvp.ui.theme.Azul1
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
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.viewModel.MedicoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicoConsulta(navController: NavController) {
    val medicoView =  MedicoViewModel()
    val medico = medicoView.getMedico(1L)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Médico - Consultar", color = Azul5) },

                )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = Azul1
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Nome: ${medico?.nomeMedico}", color = Azul5)
                    Text("CRM: ${medico?.crm}", color = Azul5)
                    Text("Especialidade: ${medico?.especialidade}", color = Azul5)
                    Text("Contato: ${medico?.contato}", color = Azul5)

                    Button(
                        onClick = { navController.navigate("MedicoIndex") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Azul4,
                            contentColor = Color.White
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
fun MedicoConsultaPreview() {
    MedicoConsulta(navController = rememberNavController())
}
