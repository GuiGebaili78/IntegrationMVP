package com.example.integrationmvp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4

@Composable
    fun Menu(
        navController: NavController // NavController para navegação
    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),  // Added padding to avoid screen edges
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center  // Center the content vertically
    ) {

        // Título MedConnect
        Text(
            text = "MedConnect",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Azul4,
            modifier = Modifier.padding(bottom = 16.dp) // Add some space between title and buttons
        )

        // Botão de Pacientes
        Button(
            onClick = {

                navController.navigate("PacienteIndex")
            },
            colors = ButtonDefaults.buttonColors(Azul4),
            modifier = Modifier
                .padding(36.dp)
                .width(250.dp)
                .shadow(10.dp, shape = RoundedCornerShape(50.dp))
        ) {
            Text(
                text = "PACIENTES",
                fontSize = 20.sp,
                color = Azul1
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Botão de Médicos
        Button(
            onClick = {

                navController.navigate("MedicoIndex")
            },
            colors = ButtonDefaults.buttonColors(Azul4),
            modifier = Modifier
                .padding(36.dp)
                .width(250.dp)
                .shadow(10.dp, shape = RoundedCornerShape(50.dp))
        ) {
            Text(
                text = "MÉDICO",
                fontSize = 20.sp,
                color = Azul1
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        // Botão de Consultas
        Button(
            onClick = {

                navController.navigate("ConsultaIndex")
            },
            colors = ButtonDefaults.buttonColors(Azul4),
            modifier = Modifier
                .padding(36.dp)
                .width(250.dp)
                .shadow(10.dp, shape = RoundedCornerShape(50.dp))
        ) {
            Text(
                text = "CONSULTAS",
                fontSize = 20.sp,
                color = Azul1
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Botão de Prontuários
        Button(
            onClick = {

                navController.navigate("ProntuarioIndex")
            },
            colors = ButtonDefaults.buttonColors(Azul4),
            modifier = Modifier
                .padding(36.dp)
                .width(250.dp)
                .shadow(10.dp, shape = RoundedCornerShape(50.dp))
        ) {
            Text(
                text = "PRONTUÁRIO",
                fontSize = 20.sp,
                color = Azul1
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Botão de Usuários
        Button(
            onClick = {

                navController.navigate("UsuarioIndex")
            },
            colors = ButtonDefaults.buttonColors(Azul4),
            modifier = Modifier
                .padding(36.dp)
                .width(250.dp)
                .shadow(10.dp, shape = RoundedCornerShape(50.dp))
        ) {
            Text(
                text = "USUÁRIOS",
                fontSize = 20.sp,
                color = Azul1
            )
        }

    }






}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun MenuPreview() {
    Menu(navController = rememberNavController())
}


