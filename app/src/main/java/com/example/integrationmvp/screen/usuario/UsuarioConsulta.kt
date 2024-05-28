package com.example.integrationmvp.screen.usuario

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
import com.example.integrationmvp.model.UsuarioModel
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.viewModel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioConsulta(navController: NavController) {
    val usuarioView =  UsuarioViewModel()
    val usuario = usuarioView.getUsuario(1L)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Usuário - Consultar", color = Azul5) },

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
                    Text("Nome: ${usuario?.nomeUsuario}", color = Azul5)
                    Text("Senha: ${usuario?.senhaUsuario}", color = Azul5)
                    Text("Email: ${usuario?.emailUsuario}", color = Azul5)

                    Button(
                        onClick = { navController.navigate("UsuarioIndex") },
                        colors = ButtonDefaults.buttonColors( // Set button colors
                            containerColor = Azul4, // New name for background color
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
fun UsuarioConsultaPreview() {
    UsuarioConsulta(navController = rememberNavController())
}
