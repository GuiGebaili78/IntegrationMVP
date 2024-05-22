package com.example.integrationmvp.screen.paciente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.integrationmvp.component.BottomNavigation
import com.example.integrationmvp.ui.theme.Azul1
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
        PacienteModel(1, "João", "123.456.789-00", "01/01/1990", "Masculino", "Av. Paulista", "123456789", "joao@example.com"),
        PacienteModel(1, "João", "123.456.789-00", "01/01/1990", "Masculino", "Av. Paulista", "123456789", "joao@example.com"),
        PacienteModel(1, "João", "123.456.789-00", "01/01/1990", "Masculino", "Av. Paulista", "123456789", "joao@example.com"),
        PacienteModel(1, "João", "123.456.789-00", "01/01/1990", "Masculino", "Av. Paulista", "123456789", "joao@example.com"),
        PacienteModel(1, "João", "123.456.789-00", "01/01/1990", "Masculino", "Av. Paulista", "123456789", "joao@example.com"),
        PacienteModel(1, "João", "123.456.789-00", "01/01/1990", "Masculino", "Av. Paulista", "123456789", "joao@example.com"),
        PacienteModel(2, "Maria", "987.654.321-00", "02/02/1995", "Feminino", "Av. Paulista", "987654321", "maria@example.com")
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacienteIndex(navController: NavController) {
    val pacientes = fetchPacientes()

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
            // Nome do aplicativo
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
                Button( // Button in the column
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
                            onEditClick = { navController.navigate("PacienteConsultar")},
                            onDeleteClick = { navController.navigate("PacienteAtualizar") },
                            onChangeClick = { navController.navigate("PacienteExcluir") }
                        )
                    }
                } else {
                    Text(
                        text = "Nenhum paciente encontrado.",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                // Spacer to push content up
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
                    Text(text = "Consultar")

                }
                Button(onClick = onDeleteClick,
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )) {
                    Text(text = "Atualizar")
                }
                Button(onClick = onChangeClick,
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
