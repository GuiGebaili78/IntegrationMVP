package com.example.integrationmvp.screen.medico


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
import com.example.integrationmvp.model.MedicoModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.ui.theme.Azul5
import com.example.integrationmvp.viewModel.MedicoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MedicoAtualizar(navController: NavController, medicoId: Long) {

    val medicoView = MedicoViewModel()
    val medico by medicoView.selectedMedico.collectAsState()

    var nome by remember { mutableStateOf("") }
    var crm by remember { mutableStateOf("") }
    var especialidade by remember { mutableStateOf("") }
    var contato by remember { mutableStateOf("") }

    medicoView.getMedico(medicoId)

    medico?.let {
        nome = it.nomeMedico ?: ""
        crm = it.crm ?: ""
        especialidade = it.especialidade ?: ""
        contato = it.contato ?: ""
    }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Atualizar Médico", color = Azul5) },
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
                            value = crm,
                            placeholder = "Digite seu CRM",
                            label = "CRM",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Number,
                            atualizarValor = { novoValor ->
                                crm = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))


                        FormComponent(
                            value = especialidade,
                            placeholder = "Digite sua especialidade",
                            label = "especialidade",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                especialidade = novoValor
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


                        Button(
                            onClick = {
                                val medicoNew = MedicoModel(
                                    medicoId = medicoId,
                                    nomeMedico = nome,
                                    crm = crm,
                                    especialidade = especialidade,
                                    contato = contato,
                                )
                                medicoView.updateMedico(medicoId, medicoNew)
                                keyboardController?.hide()
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 16.dp),
                            colors = ButtonDefaults.buttonColors( // Set button colors
                                containerColor = Azul4,
                                contentColor = Color.White // Cor do Texto do Botão
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
@Preview(showSystemUi = true, showBackground = true)
fun MedicoAtualizarPreview() {
    MedicoAtualizar(navController = rememberNavController(), medicoId = 1)
}
