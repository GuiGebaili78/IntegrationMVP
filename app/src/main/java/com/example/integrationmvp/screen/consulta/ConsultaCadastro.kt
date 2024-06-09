package com.example.integrationmvp.screen.consulta

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.integrationmvp.model.ConsultaModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.ui.theme.Azul5
import com.example.integrationmvp.viewModel.ConsultaViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ConsultaCadastro(navController: NavController, consultaViewModel: ConsultaViewModel = ConsultaViewModel()) {
    var pacienteId by remember { mutableStateOf("") }
    var medicoId by remember { mutableStateOf("") }
    var dataConsulta by remember { mutableStateOf("01/01/2023") }
    var localConsulta by remember { mutableStateOf("Hospital Morumbi") }
    var mensagem by remember { mutableStateOf("Observações") }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val medicos by consultaViewModel.medicos.collectAsState()
    val pacientes by consultaViewModel.pacientes.collectAsState()

    var expandedMedicos by remember { mutableStateOf(false) }
    var expandedPacientes by remember { mutableStateOf(false) }
    var selectedMedico by remember { mutableStateOf("") }
    var selectedPaciente by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cadastro de Consultas", color = Azul5) },
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
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(2.dp)
                            .padding(vertical = 4.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable { expandedPacientes = true }
                        ) {
                            Text(
                                text = if (selectedPaciente.isEmpty()) "Selecione o paciente" else selectedPaciente
                            )
                        }
                        DropdownMenu(
                            expanded = expandedPacientes,
                            onDismissRequest = { expandedPacientes = false }
                        ) {
                            pacientes.forEach { paciente ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedPaciente = paciente.nomePaciente ?: ""
                                        pacienteId = paciente.pacienteId.toString()
                                        expandedPacientes = false
                                    },
                                    text = {
                                        Text(text = paciente.nomePaciente ?: "")
                                    }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable { expandedMedicos = true }
                        ) {
                            Text(
                                text = if (selectedMedico.isEmpty()) "Selecione o médico" else selectedMedico
                            )
                        }
                        DropdownMenu(
                            expanded = expandedMedicos,
                            onDismissRequest = { expandedMedicos = false }
                        ) {
                            medicos.forEach { medico ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedMedico = medico.nomeMedico ?: ""
                                        medicoId = medico.medicoId.toString()
                                        expandedMedicos = false
                                    },
                                    text = {
                                        Text(text = medico.nomeMedico ?: "")
                                    }
                                )
                            }
                        }

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
                                val df = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                val longDtNascimento = LocalDate.parse(dataConsulta, df)
                                val isoDf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                val formattedDate = longDtNascimento.format(isoDf)

                                val consulta = ConsultaModel(
                                    pacienteId = pacienteId.toLong(),
                                    medicoId = medicoId.toLong(),
                                    dataConsulta = formattedDate,
                                    localConsulta = localConsulta,
                                    mensagem = mensagem,

                                )

                                consultaViewModel.createConsulta(consulta)

                                keyboardController?.hide() // Esconder teclado
                                // Navegar para outra tela
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Azul4,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Cadastrar")
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ConsultaCadastroPreview() {
    ConsultaCadastro(navController = rememberNavController())
}
