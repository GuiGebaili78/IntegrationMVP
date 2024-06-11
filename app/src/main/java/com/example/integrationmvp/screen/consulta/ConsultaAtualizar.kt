package com.example.integrationmvp.screen.consulta


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
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.ui.theme.Azul5
import com.example.integrationmvp.viewModel.ConsultaViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ConsultaAtualizar(navController: NavController, consultaId: Long) {

    val consultaView =  ConsultaViewModel()
    val consulta by consultaView.selectedConsulta.collectAsState()

    var pacienteId by remember { mutableStateOf("") }
    var medicoId by remember { mutableStateOf("") }
    var dataConsulta by remember { mutableStateOf("") }
    var horaConsulta by remember { mutableStateOf("") }
    var localConsulta by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

    consultaView.getConsulta(consultaId)

    consulta?.let {
        pacienteId = (it.pacienteId).toString()
        medicoId = (it.medicoId).toString()
        dataConsulta = it.dataConsulta?.let { isoDate ->
            val formatterIso = DateTimeFormatter.ISO_DATE_TIME
            val dateTime = LocalDateTime.parse(isoDate, formatterIso)
            val formatterDdMmYyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            dateTime.format(formatterDdMmYyyy)
        } ?: ""
        horaConsulta = it.horaConsulta ?: ""
        localConsulta = it.localConsulta ?: ""
        mensagem = it.mensagem ?: ""
    }

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
                            .padding(vertical = 4.dp)
                    ) {
                        FormComponent(
                            value = pacienteId,
                            placeholder = "Busque pelo paciente",
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
                            placeholder = "Busque pelo médico",
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
                            value = horaConsulta,
                            placeholder = "Digite o horário da consulta",
                            label = "horário",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                horaConsulta = novoValor
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
                                val longDtConsulta = LocalDate.parse(dataConsulta, df)
                                val isoDf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                val formattedDate = longDtConsulta.format(isoDf)

                                val consultaNew = ConsultaModel(
                                    consultaId = consultaId,
                                    pacienteId = pacienteId,
                                    medicoId = medicoId,
                                    dataConsulta = formattedDate,
                                    horaConsulta = horaConsulta,
                                    localConsulta = localConsulta,
                                    mensagem = mensagem,
                                )
                                consultaView.updateConsulta(consultaId, consultaNew)
                                keyboardController?.hide()
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 2.dp),
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
fun ConsultaAtualizarPreview() {
    ConsultaAtualizar(navController = rememberNavController(), consultaId = 1)
}
