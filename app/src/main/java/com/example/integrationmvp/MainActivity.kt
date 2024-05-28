package com.example.integrationmvp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.integrationmvp.screen.medico.MedicoAtualizar
import com.example.integrationmvp.screen.medico.MedicoCadastro
import com.example.integrationmvp.screen.medico.MedicoConsulta
import com.example.integrationmvp.screen.medico.MedicoIndex
import com.example.integrationmvp.screen.paciente.PacienteAtualizar
import com.example.integrationmvp.screen.paciente.PacienteCadastro
import com.example.integrationmvp.screen.paciente.PacienteConsulta
import com.example.integrationmvp.screen.paciente.PacienteIndex
import com.example.integrationmvp.screen.BemVindo
import com.example.integrationmvp.screen.Menu
import com.example.integrationmvp.screen.usuario.UsuarioAtualizar
import com.example.integrationmvp.screen.usuario.UsuarioCadastro
import com.example.integrationmvp.screen.usuario.UsuarioConsulta
import com.example.integrationmvp.screen.usuario.UsuarioIndex
import com.example.integrationmvp.ui.theme.IntegrationMVPTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntegrationMVPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "bemvindo",
                        enterTransition = { slideInHorizontally(animationSpec = tween(durationMillis = 200)) }
                    ) {

                        composable(route = "pacienteindex") {
                            PacienteIndex(navController = navController)
                        }
                        composable(route = "medicoindex") {
                            MedicoIndex(navController = navController)
                        }
                        composable(route = "usuarioindex") {
                            UsuarioIndex(navController = navController)
                        }

                        composable(route = "pacientecadastro") {
                            PacienteCadastro(navController = navController)
                        }
                        composable(route = "medicocadastro") {
                            MedicoCadastro(navController = navController)
                        }
                        composable(route = "usuariocadastro") {
                            UsuarioCadastro(navController = navController)
                        }

                        composable(route = "pacienteconsulta") {
                            PacienteConsulta(navController = navController)
                        }
                        composable(route = "medicoconsulta") {
                            MedicoConsulta(navController = navController)
                        }
                        composable(route = "usuarioconsulta") {
                            UsuarioConsulta(navController = navController)
                        }

                        composable(route = "pacienteatualizar") {
                            PacienteAtualizar(navController = navController)
                        }
                        composable(route = "medicoatualizar") {
                            MedicoAtualizar(navController = navController)
                        }
                        composable(route = "usuarioatualizar") {
                            UsuarioAtualizar(navController = navController)
                        }
                        composable(route = "menu") {
                            Menu(navController = navController)
                        }

                        composable(route = "bemvindo") {
                            BemVindo(navController = navController)
                        }



                    }
                }
            }
        }
    }
}
