package com.example.integrationmvp.screen.consulta

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
import com.example.integrationmvp.model.ConsultaModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.viewModel.ConsultaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaIndex(navController: NavController) {
    val consultaView = remember { ConsultaViewModel() }
    val consultas by consultaView.consultas.collectAsState()


    LaunchedEffect(Unit) {
        consultaView.fetchConsultas()
    }

    Log.d("ConsultaVIew", "consultas: ${consultas.toString()}")

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
                text = "Consultas",
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
                    onClick = { navController.navigate("ConsultaCadastro") },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )
                ) {
                    Text(text = "Novo Consulta")
                }

                if (consultas.isNotEmpty()) {
                    consultas.forEach { consulta ->
                        ConsultaCard(
                            consulta = consulta,
                            onEditClick = { navController.navigate("consultaatualizar/${consulta.consultaId}") },
                            onDeleteClick = { navController.navigate("consultaexcluir/${consulta.consultaId}") },

                        )
                    }
                } else {
                    Text(
                        text = "Nenhum consulta encontrado.",
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
fun ConsultaCard(
    consulta: ConsultaModel,
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
            Text(text = "Paciente: ${consulta.pacienteId}")
            Text(text = "Médico: ${consulta.medicoId}")
            Text(text = "Data: ${consulta.dataConsulta}")
            Text(text = "Hora: ${consulta.horaConsulta}")
            Text(text = "Local: ${consulta.localConsulta}")
            Text(text = "Mensagem: ${consulta.mensagem}")

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
fun ConsultaIndexPreview() {
    val navController = rememberNavController()
    ConsultaIndex(navController = navController)
}
