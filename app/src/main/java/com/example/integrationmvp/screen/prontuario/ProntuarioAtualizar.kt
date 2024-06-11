package com.example.integrationmvp.screen.prontuario


import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.collectAsState
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
import com.example.integrationmvp.model.ConsultaModel
import com.example.integrationmvp.model.ProntuarioModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.ui.theme.Azul5
import com.example.integrationmvp.viewModel.ConsultaViewModel
import com.example.integrationmvp.viewModel.ProntuarioViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ProntuarioAtualizar(navController: NavController, prontuarioId: Long) {

    val prontuarioView =  ProntuarioViewModel()
    val prontuario by prontuarioView.selectedProntuario.collectAsState()

    var pacienteId by remember { mutableStateOf("") }
    var medicoId by remember { mutableStateOf("") }
    var historicoPaciente by remember { mutableStateOf("") }
    var historicoFamiliar by remember { mutableStateOf("") }
    var medicamento by remember { mutableStateOf("") }
    var triagem by remember { mutableStateOf("") }
    var exames by remember { mutableStateOf("") }
    var dataProntuario by remember { mutableStateOf("") }

    prontuarioView.getProntuario(prontuarioId)

    prontuario?.let {
        pacienteId = (it.pacienteId).toString()
        medicoId = (it.medicoId).toString()
        historicoPaciente = it.historicoPaciente ?: ""
        historicoFamiliar = it.historicoFamiliar ?: ""
        medicamento = it.medicamento ?: ""
        triagem = it.triagem ?: ""
        exames = it.exames ?: ""
        dataProntuario = it.dataProntuario?.let { isoDate ->
            val formatterIso = DateTimeFormatter.ISO_DATE_TIME
            val dateTime = LocalDateTime.parse(isoDate, formatterIso)
            val formatterDdMmYyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            dateTime.format(formatterDdMmYyyy)
        } ?: ""

    }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Atualizar Prontuário", color = Azul5) },
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
                            .padding(vertical = 4.dp)
                    ) {
                        FormComponent(
                            value = pacienteId,
                            placeholder = "Busque pelo paciente",
                            label = "Paciente",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                pacienteId = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = medicoId,
                            placeholder = "Busque pelo médico",
                            label = "Médico",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                medicoId = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = historicoPaciente,
                            placeholder = "Digite o histórico do paciente",
                            label = "Histórico Paciente",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                historicoPaciente = novoValor
                            }
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = historicoFamiliar,
                            placeholder = "Digite o histórico familiar",
                            label = "Histórico Familiar",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                historicoFamiliar = novoValor
                            }
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = medicamento,
                            placeholder = "Digite os medicamentos do paciente",
                            label = "Medicamentos",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                medicamento = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = triagem,
                            placeholder = "Triagem",
                            label = "Triagem",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                triagem = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = exames,
                            placeholder = "Exames",
                            label = "Exames",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                exames = novoValor
                            }
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = dataProntuario,
                            placeholder = "Data do prontuário",
                            label = "Data Prontuário",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                dataProntuario = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        Button(
                            onClick = {

                                val df = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                val longDtProntuario = LocalDate.parse(dataProntuario, df)
                                val isoDf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                val formattedDate = longDtProntuario.format(isoDf)

                                val prontuarioNew = ProntuarioModel(
                                    prontuarioId = prontuarioId,
                                    pacienteId = pacienteId,
                                    medicoId = medicoId,
                                    historicoPaciente = historicoPaciente,
                                    historicoFamiliar = historicoFamiliar,
                                    medicamento = medicamento,
                                    triagem = triagem,
                                    exames = exames,
                                    dataProntuario = formattedDate,
                                )
                                prontuarioView.updateProntuario(prontuarioId, prontuarioNew)
                                keyboardController?.hide()
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview (showSystemUi = true, showBackground = true)
fun ProntuarioAtualizarPreview() {
    ProntuarioAtualizar(navController = rememberNavController(), prontuarioId = 1)
}
