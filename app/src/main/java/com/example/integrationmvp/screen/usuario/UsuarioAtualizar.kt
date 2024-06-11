package com.example.integrationmvp.screen.usuario

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
import com.example.integrationmvp.model.UsuarioModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.ui.theme.Azul5
import com.example.integrationmvp.viewModel.UsuarioViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UsuarioAtualizar(navController: NavController, usuarioId: Long) {

    val usuarioView =  UsuarioViewModel()
    val paciente by usuarioView.selectedUsuario.collectAsState()

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    usuarioView.getUsuario(usuarioId)

    paciente?.let {
        nome = it.nomeUsuario ?: ""
        email = it.emailUsuario ?: ""
        senha = it.senhaUsuario ?: ""
    }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Atualizar Usuário", color = Azul5) },
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
                            value = email,
                            placeholder = "Digite seu email",
                            label = "Email",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                email = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        FormComponent(
                            value = senha,
                            placeholder = "Digite sua senha",
                            label = "Senha",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            keyboardType = KeyboardType.Text,
                            atualizarValor = { novoValor ->
                                senha = novoValor
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))


                        Button(
                            onClick = {

                                val usuarioNew = UsuarioModel(
                                    usuarioId = usuarioId,
                                    nomeUsuario = nome,
                                    emailUsuario = email,
                                    senhaUsuario = senha,
                                )
                                usuarioView.updateUsuario(usuarioId, usuarioNew)
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
fun UsuarioAtualizarPreview() {
    UsuarioAtualizar(navController = rememberNavController(), usuarioId = 1)
}
