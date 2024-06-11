package com.example.integrationmvp.screen.prontuario

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
import com.example.integrationmvp.model.ProntuarioModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.viewModel.ProntuarioViewModel
import com.example.integrationmvp.viewModel.PacienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProntuarioIndex(navController: NavController) {
    val prontuarioView = remember { ProntuarioViewModel() }
    val prontuarios by prontuarioView.prontuarios.collectAsState()


    LaunchedEffect(Unit) {
        prontuarioView.fetchProntuarios()
    }

    LaunchedEffect(prontuarios) {
        Log.d("ProntuarioView", "prontuarios: ${prontuarios.toString()}")
    }

    Log.d("ProntuarioView", "prontuarios: ${prontuarios.toString()}")

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
                text = "Prontuarios",
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
                    onClick = { navController.navigate("ProntuarioCadastro") },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )
                ) {
                    Text(text = "Novo Prontuario")
                }

                if (prontuarios.isNotEmpty()) {
                    prontuarios.forEach { prontuario ->
                        ProntuarioCard(
                            prontuario = prontuario,
                            onEditClick = { navController.navigate("prontuarioatualizar/${prontuario.prontuarioId}") },
                            onDeleteClick = {prontuarioView.deleteProntuario(prontuario.prontuarioId)
                                prontuarioView.fetchProntuarios()},

                        )
                    }
                } else {
                    Text(
                        text = "Nenhum prontuario encontrado.",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
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
fun ProntuarioCard(
    prontuario: ProntuarioModel,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Azul1)
        ) {
            Text(text = "Paciente: ${prontuario.pacienteId}")
            Text(text = "Médico: ${prontuario.medicoId}")
            Text(text = "Histórico Paciente: ${prontuario.historicoPaciente}")
            Text(text = "Histórico Familiar: ${prontuario.historicoFamiliar}")
            Text(text = "Medicamentos: ${prontuario.medicamento}")
            Text(text = "Triagem: ${prontuario.triagem}")
            Text(text = "Exames: ${prontuario.exames}")
            Text(text = "Data Prontuário: ${prontuario.dataProntuario}")

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

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
fun ProntuarioIndexPreview() {
    val navController = rememberNavController()
    ProntuarioIndex(navController = navController)
}
