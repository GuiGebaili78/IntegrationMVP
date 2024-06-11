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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
import com.example.integrationmvp.screen.consulta.ConsultaCadastro
import com.example.integrationmvp.screen.consulta.ConsultaIndex
import com.example.integrationmvp.screen.prontuario.ProntuarioCadastro
import com.example.integrationmvp.screen.prontuario.ProntuarioIndex
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



                        //Navegação Paciente

                        composable(route = "pacienteindex") {
                            PacienteIndex(navController = navController)
                        }
                        composable(route = "pacientecadastro") {
                            PacienteCadastro(navController = navController)
                        }
                        composable(route = "pacienteconsulta/{pacienteId}", arguments = listOf(
                            navArgument("pacienteId") { type = NavType.LongType })) {
                                backStackEntry ->
                            PacienteConsulta(
                                navController = navController,
                                pacienteId = backStackEntry.arguments?.getLong("pacienteId") ?: -1L
                            )
                        }
                        composable(route = "pacienteatualizar/{pacienteId}", arguments = listOf(
                            navArgument("pacienteId") { type = NavType.LongType })) {
                                backStackEntry ->
                            PacienteAtualizar(
                                navController = navController,
                                pacienteId = backStackEntry.arguments?.getLong("pacienteId") ?: -1L
                            )
                        }




                        //Navegação Médico

                        composable(route = "medicoindex") {
                            MedicoIndex(navController = navController)
                        }
                        composable(route = "medicocadastro") {
                            MedicoCadastro(navController = navController)
                        }
                        composable(route = "medicoconsulta") {
                            MedicoConsulta(navController = navController)
                        }
                        composable(route = "medicoatualizar") {
                            MedicoAtualizar(navController = navController)
                        }



                        //Navegação Consulta

                        composable(route = "consultaindex") {
                            ConsultaIndex(navController = navController)
                        }
                        composable(route = "consultacadastro") {
                            ConsultaCadastro(navController = navController)
                        }
                        composable(route = "consultaconsulta") {
                            //ConsultaConsulta(navController = navController)
                        }
                        composable(route = "consultaatualizar") {
                            //ConsultaAtualizar(navController = navController)
                        }




                        //Navegação Usuario

                        composable(route = "usuarioindex") {
                            UsuarioIndex(navController = navController)
                        }
                        composable(route = "usuariocadastro") {
                            UsuarioCadastro(navController = navController)
                        }
                        composable(route = "usuarioconsulta") {
                            UsuarioConsulta(navController = navController)
                        }

                        composable(route = "usuarioatualizar") {
                            UsuarioAtualizar(navController = navController)
                        }




                        //Navegação Prontuário

                        composable(route = "prontuarioindex") {
                            ProntuarioIndex(navController = navController)
                        }
                        composable(route = "prontuariocadastro") {
                            ProntuarioCadastro(navController = navController)
                        }/*
                        composable(route = "prontuarioconsulta") {
                            ProntuarioConsulta(navController = navController)
                        }

                        composable(route = "prontuarioatualizar") {
                            ProntuarioAtualizar(navController = navController)
                        }*/






                        //Navegação App

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
