package com.example.integrationmvp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul2
import com.example.integrationmvp.ui.theme.Azul3
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.ui.theme.Azul5

// Modelo de dados simulado
data class PacienteModel(
    val id: Int,
    val nome: String,
    val cpf: String,
    val dataNascimento: String,
    val genero: String,
    val endereco: String,
    val contato: String,
    val email: String
)

// Função para simular o carregamento de dados
fun fetchPacientes(): List<PacienteModel> {
    // Implemente aqui a lógica para carregar os pacientes do banco de dados ou de uma fonte de dados
    return listOf(
        PacienteModel(1, "João", "123.456.789-00", "01/01/1990", "Masculino", "Av. Paulista", "123456789", "joao@example.com"),
        PacienteModel(2, "Maria", "987.654.321-00", "02/02/1995", "Feminino", "Av. Paulista", "987654321", "maria@example.com")
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacienteIndex(navController: NavController) {
    val pacientes = fetchPacientes()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pacientes",
                        color = Azul5,
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp) // Adicionando o padding aqui
                .verticalScroll(rememberScrollState())
        ) {
            Button( // Button in the column
                onClick = { /* Implemente a lógica para adicionar novo paciente */ },
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
                        onEditClick = { /* Implemente a lógica para editar o paciente */ },
                        onDeleteClick = { /* Implemente a lógica para excluir o paciente */ },
                        onChangeClick = { /* Implemente a lógica para alterar o paciente */ }
                    )
                }
            } else {
                Text(
                    text = "Nenhum paciente encontrado.",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacienteCard(
    paciente: PacienteModel,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onChangeClick: () -> Unit
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
            Text(text = "ID: ${paciente.id}")
            Text(text = "Nome: ${paciente.nome}")
            Text(text = "CPF: ${paciente.cpf}")
            Text(text = "Data de Nascimento: ${paciente.dataNascimento}")
            Text(text = "Gênero: ${paciente.genero}")
            Text(text = "Endereço: ${paciente.endereco}")
            Text(text = "Contato: ${paciente.contato}")
            Text(text = "Email: ${paciente.email}")

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
                    Text(text = "Editar")

                }
                Button(onClick = onDeleteClick,
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )) {
                    Text(text = "Excluir")
                }
                Button(onClick = onChangeClick,
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )) {
                    Text(text = "Alterar")

                }
            }
        }
    }
}


@Preview("Paciente Index Preview")
@Composable
fun PacienteIndexPreview() {
    val navController = rememberNavController()
    PacienteIndex(navController = navController)
}
