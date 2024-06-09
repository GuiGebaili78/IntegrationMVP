package com.example.integrationmvp.screen.consulta


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.integrationmvp.component.BottomNavigation
import com.example.integrationmvp.component.FormComponent
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.ui.theme.Azul5
import com.example.integrationmvp.viewModel.ConsultaViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ConsultaAtualizar(navController: NavController) {

    //val consultaView =  ConsultaViewModel()
    //val consulta = consultaView.getConsulta(id)

    var pacienteId by remember { mutableStateOf("") }
    var medicoId by remember { mutableStateOf("") }
    var dataConsulta by remember { mutableStateOf("") }
    var localConsulta by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Atualizar Consulta", color = Azul5) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Back")
                    }
                }
            )
        },
        content = { contentPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                color = Azul1
            ) {
                Box (
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(2.dp)
                            .padding(vertical = 4.dp) // Adicionando espaçamento vertical menor
                    ) {
                        FormComponent(
                            value = pacienteId,
                            placeholder = "Digite o nome do paciente",
                            label = "Paciente",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                pacienteId = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = medicoId,
                            placeholder = "Digite o nome do médico",
                            label = "Médico",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                medicoId = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = dataConsulta,
                            placeholder = "Digite a data da consulta",
                            label = "Data",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                dataConsulta = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = localConsulta,
                            placeholder = "Digite o local",
                            label = "Local",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                localConsulta = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = mensagem,
                            placeholder = "Observações",
                            label = "Observações",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                mensagem = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        Button(
                            onClick = {

                                keyboardController?.hide()

                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Azul4,
                                contentColor = Color.White
                            )


                        ) {
                            Text(text = "Alterar")
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
        }
    )
}

@Composable
@Preview (showSystemUi = true, showBackground = true)
fun ConsultaAtualizarPreview() {
    ConsultaAtualizar(navController = rememberNavController())
}
