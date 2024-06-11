package com.example.integrationmvp.screen.medico

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
import com.example.integrationmvp.model.MedicoModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.viewModel.MedicoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicoIndex(navController: NavController) {
    val medicoView = remember { MedicoViewModel() }
    val medicos by medicoView.medicos.collectAsState()

    // Use LaunchedEffect to fetch medicos only once
    LaunchedEffect(Unit) {
        medicoView.fetchMedicos()
    }

    Log.d("MedicoVIew", "medicos: ${medicos.toString()}")

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
                text = "Medicos",
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
                    onClick = { navController.navigate("MedicoCadastro") },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )
                ) {
                    Text(text = "Novo Medico")
                }

                if (medicos.isNotEmpty()) {
                    medicos.forEach { medico ->
                        MedicoCard(
                            medico = medico,
                            onEditClick = { navController.navigate("medicoatualizar/${medico.medicoId}") },
                            onDeleteClick = { navController.navigate("medicoexcluir/${medico.medicoId}") },

                        )
                    }
                } else {
                    Text(
                        text = "Nenhum medico encontrado.",
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
fun MedicoCard(
    medico: MedicoModel,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,

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

            Text(text = "Nome: ${medico.nomeMedico}")
            Text(text = "CRM: ${medico.crm}")
            Text(text = "Especialidade: ${medico.especialidade}")
            Text(text = "Contato: ${medico.contato}")

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
fun MedicoIndexPreview() {
    val navController = rememberNavController()
    MedicoIndex(navController = navController)
}
