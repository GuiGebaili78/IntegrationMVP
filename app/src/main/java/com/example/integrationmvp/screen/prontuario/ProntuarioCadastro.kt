package com.example.integrationmvp.screen.prontuario

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
import com.example.integrationmvp.model.ProntuarioModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.ui.theme.Azul5
import com.example.integrationmvp.viewModel.ProntuarioViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ProntuarioCadastro(navController: NavController, prontuarioViewModel: ProntuarioViewModel = ProntuarioViewModel()) {
    var pacienteId by remember { mutableStateOf("") }
    var medicoId by remember { mutableStateOf("") }
    var historicoPaciente by remember { mutableStateOf("") }
    var historicoFamiliar by remember { mutableStateOf("") }
    var medicamento by remember { mutableStateOf("") }
    var triagem by remember { mutableStateOf("") }
    var exames by remember { mutableStateOf("") }
    var dataProntuario by remember { mutableStateOf("01/01/2023") }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val medicos by prontuarioViewModel.medicos.collectAsState()
    val pacientes by prontuarioViewModel.pacientes.collectAsState()

    var expandedMedicos by remember { mutableStateOf(false) }
    var expandedPacientes by remember { mutableStateOf(false) }
    var selectedMedico by remember { mutableStateOf("") }
    var selectedPaciente by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cadastro de Prontuarios", color = Azul5) },
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
                            value = historicoPaciente,
                            placeholder = "Digite o histórico",
                            label = "Histórico Paciente",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                historicoPaciente = novoValor
                            }
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = historicoFamiliar,
                            placeholder = "Digite o histórico",
                            label = "Histórico Familiar",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                historicoFamiliar = novoValor
                            }
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = medicamento,
                            placeholder = "Digite os medicamentos",
                            label = "Medicamento",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                medicamento = novoValor
                            }
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = triagem,
                            placeholder = "Dados da triagem",
                            label = "Triagem",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                triagem = novoValor
                            }
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = exames,
                            placeholder = "Digite o exames",
                            label = "Exames",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                exames = novoValor
                            }
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = dataProntuario,
                            placeholder = "Digite a data do prontuário",
                            label = "Data",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                dataProntuario = novoValor
                            }
                        )


                        Spacer(modifier = Modifier.height(2.dp))

                        Button(
                            onClick = {
                                val df = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                val longDtNascimento = LocalDate.parse(dataProntuario, df)
                                val isoDf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                val formattedDate = longDtNascimento.format(isoDf)

                                val prontuario = ProntuarioModel(
                                    pacienteId = pacienteId.toLong(),
                                    medicoId = medicoId.toLong(),
                                    historicoPaciente = historicoPaciente,
                                    historicoFamiliar = historicoFamiliar,
                                    medicamento = medicamento,
                                    triagem = triagem,
                                    exames = exames,
                                    dataProntuario = formattedDate,

                                    )

                                prontuarioViewModel.createProntuario(prontuario)

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
fun ProntuarioCadastroPreview() {
    ProntuarioCadastro(navController = rememberNavController())
}
