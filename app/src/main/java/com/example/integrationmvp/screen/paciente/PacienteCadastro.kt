package com.example.integrationmvp.screen.paciente

import android.icu.text.SimpleDateFormat
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
import com.example.integrationmvp.model.PacienteModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.ui.theme.Azul5
import com.example.integrationmvp.viewModel.PacienteViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PacienteCadastro(navController: NavController) {
    var nome by remember { mutableStateOf("Paciente Teste 02") }
    var cpf by remember { mutableStateOf("40288275829") }
    var dataNascimento by remember { mutableStateOf("01/01/1999") }
    var genero by remember { mutableStateOf("m") }
    var endereco by remember { mutableStateOf("rua x 123") }
    var contato by remember { mutableStateOf("1198888888") }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cadastro de Pacientes", color = Azul5) },
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
                            value = nome,
                            placeholder = "Digite seu nome",
                            label = "Nome",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                nome = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = cpf,
                            placeholder = "Digite seu CPF",
                            label = "CPF",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                cpf = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = dataNascimento,
                            placeholder = "Digite sua data de nascimento",
                            label = "Nascimento",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                dataNascimento = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = genero,
                            placeholder = "Digite seu gênero",
                            label = "Gênero",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                genero = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = endereco,
                            placeholder = "Digite seu endereço",
                            label = "endereço",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                endereco = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = contato,
                            placeholder = "Digite seu contato",
                            label = "Contato",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                contato = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))


                        // Repeat TextField for other fields (cpf, dataNascimento, genero, endereco, contato, email)

                        Button(
                            onClick = {
                                val df = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                val longDtNascimento = LocalDate.parse(dataNascimento, df)
                                val isoDf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                val formattedDate = longDtNascimento.format(isoDf)

                                val paciente = PacienteModel(
                                    nomePaciente = nome,
                                    cpf = cpf,
                                    dataNascimento = formattedDate,
                                    genero = genero,
                                    endereco = endereco,
                                    contato = contato,
                                )

                                val pacienteView = PacienteViewModel()
                                pacienteView.createPaciente(paciente)

                                // Logic to save the patient data
                                keyboardController?.hide() // Hide keyboard
                                // Navigate back or to another screen
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 16.dp),
                            colors = ButtonDefaults.buttonColors( // Set button colors
                                containerColor = Azul4, // New name for background color
                                contentColor = Color.White // Cor do Texto do Botão
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
@Preview (showSystemUi = true, showBackground = true)
fun PacienteCadastroPreview() {
    PacienteCadastro(navController = rememberNavController())
}
