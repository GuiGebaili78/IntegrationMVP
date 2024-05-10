package com.example.integrationmvp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.integrationmvp.screen.PacienteCadastro
import com.example.integrationmvp.screen.PacienteConsulta
import com.example.integrationmvp.screen.PacienteIndex
import com.example.integrationmvp.ui.theme.IntegrationMVPTheme

class MainActivity : ComponentActivity() {
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
                        startDestination = "pacienteindex",
                        enterTransition = { slideInHorizontally(animationSpec = tween(durationMillis = 200)) }
                    ) {

                        composable(route = "pacienteindex") {
                            PacienteIndex(navController = navController)
                        }

                        composable(route = "pacientecadastro") {
                            PacienteCadastro(navController = navController)
                        }

                        composable(route = "pacienteconsulta") {
                            PacienteConsulta(navController = navController)
                        }


                    }
                }
            }
        }
    }
}
