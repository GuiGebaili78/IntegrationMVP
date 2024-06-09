package com.example.integrationmvp.screen.paciente

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.integrationmvp.component.BottomNavigation
import com.example.integrationmvp.model.PacienteModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.viewModel.PacienteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacienteIndex(navController: NavController) {
    val pacienteView = remember { PacienteViewModel() }
    val pacientes by pacienteView.pacientes.collectAsState()

    // Use LaunchedEffect to fetch pacientes only once
    LaunchedEffect(Unit) {
        pacienteView.fetchPacientes()
    }

    Log.d("PacienteVIew", "pacientes: ${pacientes.toString()}")

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Pacientes",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Azul4,
                modifier = Modifier
                    .padding(bottom = 46.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Button(
                    onClick = { navController.navigate("PacienteCadastro") },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )
                ) {
                    Text(text = "Novo Paciente")
                }

                if (pacientes.isNotEmpty()) {
                    pacientes.forEach { paciente ->
                        PacienteCard(
                            paciente = paciente,
                            onEditClick = { navController.navigate("pacienteatualizar/${paciente.pacienteId}") },
                            onDeleteClick = { navController.navigate("pacienteexcluir/${paciente.pacienteId}") },
                            onDetailClick = { navController.navigate("pacienteconsulta/${paciente.pacienteId}") }
                        )
                    }
                } else {
                    Text(
                        text = "Nenhum paciente encontrado.",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp)) // Add some space
            }
        }

        BottomNavigation(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacienteCard(
    paciente: PacienteModel,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onDetailClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column( // Wrap content with a Column
            modifier = Modifier
                .background(Azul1) // Set text color for content inside
        ) {

            Text(text = "Nome: ${paciente.nomePaciente}")
            Text(text = "CPF: ${paciente.cpf}")
            Text(text = "Data de Nascimento: ${paciente.dataNascimento}")
            Text(text = "Gênero: ${paciente.genero}")
            Text(text = "Endereço: ${paciente.endereco}")
            Text(text = "Contato: ${paciente.contato}")

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onDetailClick,
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )) {
                    Text(text = "Consultar")

                }
                Button(onClick = onEditClick,
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )) {
                    Text(text = "Atualizar")
                }
                Button(onClick = onDeleteClick,
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )) {
                    Text(text = "Excluir")

                }
            }
        }
    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PacienteIndexPreview() {
    val navController = rememberNavController()
    PacienteIndex(navController = navController)
}
